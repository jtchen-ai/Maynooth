using System;
using System.Drawing;
using System.Windows.Forms;
using System.Media;
using System.Runtime.InteropServices;

namespace Pong
{
    public partial class Form1 : Form
    {
        // 游戏状态变量
        Point Ball;
        Point Ball_Direction;
        int Paddle_Left_Y = 128;
        int Paddle_Right_Y = 128;
        bool Game_paused = true;
        int Left_score = 0;
        int Right_score = 0;

        SoundPlayer plickSound;
        SoundPlayer plunkSound;

        // === 控件声明 ===
        private Label label1;
        private Label label2;
        private Timer timer1;

        [DllImport("user32.dll")]
        static extern ushort GetAsyncKeyState(int vKey);

        public static bool IsKeyPushedDown(Keys keyData)
        {
            return 0 != (GetAsyncKeyState((int)keyData) & 0x8000);
        }

        public Form1()
        {
            // 这个方法通常由设计器维护，但由于我们完全用代码创建控件，
            // 确保它不会与我们的代码冲突。
            // 如果 InitializeComponent() 在 Form1.Designer.cs 中仍然尝试创建
            // pictureBox1, label1, label2, timer1，则会导致问题。
            // 最好的做法是确保 Form1.Designer.cs 中的 InitializeComponent 是干净的
            // (只包含窗体级别的设置，或为空，如果所有东西都在这里创建)。
            InitializeComponent(); // 标准调用

            // === 程序化创建和配置控件 ===
            this.SuspendLayout(); // 暂停布局逻辑，优化控件添加性能

            // 1. 设置窗体属性
            this.ClientSize = new System.Drawing.Size(740, 340);
            this.Text = "Pong";
            this.BackColor = Color.FromArgb(20, 20, 20); // 深灰色背景
            this.FormBorderStyle = FormBorderStyle.FixedSingle; // 禁止调整大小
            this.MaximizeBox = false; // 禁止最大化

            // 2. 创建和配置 PictureBox (游戏区域)
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Location = new System.Drawing.Point(90, 20);
            this.pictureBox1.Size = new System.Drawing.Size(512, 256);
            this.pictureBox1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pictureBox1.BackColor = Color.Black;
            this.pictureBox1.TabStop = false;
            this.Controls.Add(this.pictureBox1);

            // 3. 创建和配置 Label1 (左边得分)
            this.label1 = new System.Windows.Forms.Label();
            this.label1.Name = "label1";
            this.label1.Font = new System.Drawing.Font("Arial Narrow", 48F, System.Drawing.FontStyle.Bold);
            this.label1.Text = "0";
            this.label1.AutoSize = true;
            this.label1.ForeColor = Color.White;
            this.Controls.Add(this.label1); // 添加后再设置位置，因为AutoSize会影响尺寸
            this.label1.Location = new System.Drawing.Point(this.pictureBox1.Left - this.label1.Width - 20 > 5 ? this.pictureBox1.Left - this.label1.Width - 20 : 5,
                                                       this.pictureBox1.Top + (this.pictureBox1.Height - this.label1.Height) / 2);

            // 4. 创建和配置 Label2 (右边得分)
            this.label2 = new System.Windows.Forms.Label();
            this.label2.Name = "label2";
            this.label2.Font = new System.Drawing.Font("Arial Narrow", 48F, System.Drawing.FontStyle.Bold);
            this.label2.Text = "0";
            this.label2.AutoSize = true;
            this.label2.ForeColor = Color.White;
            this.Controls.Add(this.label2); // 添加后再设置位置
            this.label2.Location = new System.Drawing.Point(this.pictureBox1.Right + 20,
                                                       this.pictureBox1.Top + (this.pictureBox1.Height - this.label2.Height) / 2);

            // 5. 创建和配置 Timer
            this.timer1 = new System.Windows.Forms.Timer();
            this.timer1.Enabled = true;
            this.timer1.Interval = 10; // 10ms for ~100 FPS, PDF uses this
            this.timer1.Tick += new System.EventHandler(this.timer1_Tick);

            this.ResumeLayout(false); // 恢复布局逻辑
            this.PerformLayout();

            // 初始化 SoundPlayer
            try
            {
                // 确保声音文件在执行目录下 (bin\Debug 或 bin\Release)
                plickSound = new SoundPlayer(@"plick.wav");
                plunkSound = new SoundPlayer(@"plunk.wav");
                plickSound.LoadAsync(); // 异步加载，避免阻塞UI
                plunkSound.LoadAsync();
            }
            catch (Exception ex)
            {
                MessageBox.Show("加载声音文件失败: " + ex.Message + "\n请确保 plick.wav 和 plunk.wav 在程序运行目录下。", "声音错误", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                plickSound = null;
                plunkSound = null;
            }

            // 初始化游戏状态变量
            ResetGameInitial(); // 调用一个方法来设置初始游戏状态
        }

        private void ResetGameInitial()
        {
            Ball = new Point(pictureBox1.Width / 2, pictureBox1.Height / 2); // 球从中间开始
            Ball_Direction = new Point(3, 3); // 初始速度
            Paddle_Left_Y = pictureBox1.Height / 2;
            Paddle_Right_Y = pictureBox1.Height / 2;
            Game_paused = true;
            Left_score = 0;
            Right_score = 0;
            if (label1 != null) label1.Text = Left_score.ToString("0");
            if (label2 != null) label2.Text = Right_score.ToString("0");

            // 强制重绘初始状态
            if (pictureBox1.Width > 0 && pictureBox1.Height > 0)
            {
                DrawGameState();
            }
        }

        private void ResetForNewPoint(bool leftPlayerScored)
        {
            Game_paused = true;
            if (leftPlayerScored)
            {
                Ball = new Point(pictureBox1.Width - 30 - 10, Paddle_Right_Y); // 右边发球 (PDF: Ball.X = 480)
                Ball_Direction = new Point(-3, 3); // 向左发球
            }
            else
            {
                Ball = new Point(30 + 10, Paddle_Left_Y); // 左边发球 (PDF: Ball.X = 30)
                Ball_Direction = new Point(3, 3); // 向右发球
            }
            DrawGameState();
        }


        private void timer1_Tick(object sender, EventArgs e)
        {
            // Paddle Movement Logic
            if (IsKeyPushedDown(Keys.W)) Paddle_Left_Y -= 10;
            if (IsKeyPushedDown(Keys.X)) Paddle_Left_Y += 10;
            if (IsKeyPushedDown(Keys.P)) Paddle_Right_Y -= 10;
            if (IsKeyPushedDown(Keys.M)) Paddle_Right_Y += 10;

            int paddleHalfHeight = 50;
            if (Paddle_Left_Y < paddleHalfHeight) Paddle_Left_Y = paddleHalfHeight;
            if (Paddle_Left_Y > pictureBox1.Height - paddleHalfHeight) Paddle_Left_Y = pictureBox1.Height - paddleHalfHeight;
            if (Paddle_Right_Y < paddleHalfHeight) Paddle_Right_Y = paddleHalfHeight;
            if (Paddle_Right_Y > pictureBox1.Height - paddleHalfHeight) Paddle_Right_Y = pictureBox1.Height - paddleHalfHeight;

            if (IsKeyPushedDown(Keys.Space)) Game_paused = false;
            if (IsKeyPushedDown(Keys.Escape)) Application.Exit();

            // Ball/Paddle collision
            // Left paddle: X is 10, width is 10. Collision at X=20. Ball center X is Ball.X. Ball radius is 10.
            // Ball's left edge Ball.X-10. Paddle's right edge 10+10=20.
            // Collision if Ball.X-10 <= 20 AND Ball.X-10 > 10 (to avoid sticking)
            if (Ball_Direction.X < 0 && Ball.X - 10 <= 20 && Ball.X + 10 > 10 && Math.Abs(Ball.Y - Paddle_Left_Y) < paddleHalfHeight + 10)
            {
                Ball.X = 20 + 10; // Place ball just outside paddle
                Ball_Direction.X = -Ball_Direction.X;
                if (plickSound != null) plickSound.Play();
            }

            // Right paddle: X is pictureBox1.Width-20, width is 10. Collision at X=pictureBox1.Width-20.
            // Ball's right edge Ball.X+10. Paddle's left edge pictureBox1.Width-20.
            if (Ball_Direction.X > 0 && Ball.X + 10 >= pictureBox1.Width - 20 && Ball.X - 10 < pictureBox1.Width - 10 && Math.Abs(Ball.Y - Paddle_Right_Y) < paddleHalfHeight + 10)
            {
                Ball.X = pictureBox1.Width - 20 - 10; // Place ball just outside paddle
                Ball_Direction.X = -Ball_Direction.X;
                if (plickSound != null) plickSound.Play();
            }

            if (!Game_paused)
            {
                Ball.X += Ball_Direction.X;
                Ball.Y += Ball_Direction.Y;

                // Wall collisions
                if (Ball.X + 10 >= pictureBox1.Width) // Right wall
                {
                    Left_score++;
                    if (plunkSound != null) plunkSound.Play();
                    ResetForNewPoint(false); // Left player scored, so right player (false) serves next (actually means left player serves)
                    // Let's re-evaluate: if Ball hits right wall, Left_score++. Ball should be reset for Left player to serve.
                    // PDF: Ball.X = 30; Ball.Y = Paddle_Left_Y;
                }
                else if (Ball.X - 10 <= 0) // Left wall
                {
                    Right_score++;
                    if (plunkSound != null) plunkSound.Play();
                    ResetForNewPoint(true); // Right player scored, so left player (true) serves next
                    // PDF: Ball.X = 480; Ball.Y = Paddle_Right_Y;
                }

                if (Ball.Y + 10 >= pictureBox1.Height) // Bottom wall
                {
                    Ball.Y = pictureBox1.Height - 10; // Prevent sticking
                    Ball_Direction.Y = -Ball_Direction.Y;
                    if (plunkSound != null) plunkSound.Play();
                }
                else if (Ball.Y - 10 <= 0) // Top wall
                {
                    Ball.Y = 10; // Prevent sticking
                    Ball_Direction.Y = -Ball_Direction.Y;
                    if (plunkSound != null) plunkSound.Play();
                }
            }

            DrawGameState(); // Call common drawing method

            label1.Text = Left_score.ToString("0");
            label2.Text = Right_score.ToString("0");

            if ((Left_score > 2) || (Right_score > 2))
            {
                timer1.Stop();
                String winnerText = (Left_score > Right_score) ? "Left" : "Right";
                DialogResult reply = MessageBox.Show(winnerText + " player wins!\r\nDo you wish to play again?",
                                                    "Game Over!", MessageBoxButtons.YesNo);
                if (reply == DialogResult.Yes)
                {
                    ResetGameInitial();
                    timer1.Start();
                }
                else
                {
                    Application.Exit();
                }
            }
        }

        private void DrawGameState()
        {
            if (pictureBox1.Width <= 0 || pictureBox1.Height <= 0) return; // Avoid drawing on invalid surface

            Bitmap image = new Bitmap(pictureBox1.Width, pictureBox1.Height);
            using (Graphics g = Graphics.FromImage(image))
            {
                g.Clear(pictureBox1.BackColor); // Clear with PictureBox background color

                // Draw Ball
                g.FillEllipse(Brushes.Red, Ball.X - 10, Ball.Y - 10, 20, 20);

                // Draw Paddles
                int paddleHalfHeight = 50;
                g.FillRectangle(Brushes.Blue, 10, Paddle_Left_Y - paddleHalfHeight, 10, 100);
                g.FillRectangle(Brushes.Blue, pictureBox1.Width - 20, Paddle_Right_Y - paddleHalfHeight, 10, 100);

                // Draw center line (optional)
                using (Pen dashedPen = new Pen(Color.Gray, 2))
                {
                    dashedPen.DashStyle = System.Drawing.Drawing2D.DashStyle.Dash;
                    g.DrawLine(dashedPen, pictureBox1.Width / 2, 0, pictureBox1.Width / 2, pictureBox1.Height);
                }
            }
            if (pictureBox1.Image != null)
            {
                pictureBox1.Image.Dispose();
            }
            pictureBox1.Image = image;
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            +
        }
    }
}