#pragma once
#include <time.h>

namespace WFA_APP2 {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	clock_t start_time, current_time;
    // int count = 0;

	public ref class Form1 : public System::Windows::Forms::Form
	{
	public:
		Form1(void)
		{
			InitializeComponent();
			this->timer1->Enabled = false; 
			this->label1->Text = L"0.0";
			this->label1->Font = (gcnew System::Drawing::Font(L"Arial", 48, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
		}

	protected:
		~Form1()
		{
			if (components)
			{
				delete components;
			}
		}
	private: System::Windows::Forms::Button^  buttonStart;
	private: System::Windows::Forms::Label^  label1;
	private: System::Windows::Forms::Timer^  timer1;
	private: System::Windows::Forms::Button^  buttonStop;
	private: System::ComponentModel::IContainer^  components;

#pragma region Windows Form Designer generated code
		void InitializeComponent(void)
		{
			this->components = (gcnew System::ComponentModel::Container());
			this->buttonStart = (gcnew System::Windows::Forms::Button());
			this->label1 = (gcnew System::Windows::Forms::Label());
			this->timer1 = (gcnew System::Windows::Forms::Timer(this->components));
			this->buttonStop = (gcnew System::Windows::Forms::Button());
			this->SuspendLayout();
			// 
			// buttonStart
			// 
			this->buttonStart->Location = System::Drawing::Point(50, 200);
			this->buttonStart->Name = L"buttonStart";
			this->buttonStart->Size = System::Drawing::Size(100, 30);
			this->buttonStart->TabIndex = 0;
			this->buttonStart->Text = L"Start";
			this->buttonStart->UseVisualStyleBackColor = true;
			this->buttonStart->Click += gcnew System::EventHandler(this, &Form1::buttonStart_Click);
			// 
			// label1
			// 
			this->label1->AutoSize = true;
			this->label1->Font = (gcnew System::Drawing::Font(L"Arial", 48, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point, static_cast<System::Byte>(0)));
			this->label1->Location = System::Drawing::Point(50, 50);
			this->label1->Name = L"label1";
			this->label1->Size = System::Drawing::Size(109, 90);
			this->label1->TabIndex = 1;
			this->label1->Text = L"0.0";
			// 
			// timer1
			// 
			this->timer1->Interval = 100;
			this->timer1->Tick += gcnew System::EventHandler(this, &Form1::timer1_Tick);
			// 
			// buttonStop
			// 
			this->buttonStop->Location = System::Drawing::Point(170, 200);
			this->buttonStop->Name = L"buttonStop";
			this->buttonStop->Size = System::Drawing::Size(100, 30);
			this->buttonStop->TabIndex = 2;
			this->buttonStop->Text = L"Stop";
			this->buttonStop->UseVisualStyleBackColor = true;
			this->buttonStop->Click += gcnew System::EventHandler(this, &Form1::buttonStop_Click);
			// 
			// Form1
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(8, 16);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(320, 300);
			this->Controls->Add(this->buttonStop);
			this->Controls->Add(this->label1);
			this->Controls->Add(this->buttonStart);
			this->Name = L"Form1";
			this->Text = L"Stopwatch";
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
	private: System::Void buttonStart_Click(System::Object^  sender, System::EventArgs^  e) {
				 this->timer1->Start();
				 start_time = clock();
				 //label1->Text="Hello";
			 }

	private: System::Void timer1_Tick(System::Object^  sender, System::EventArgs^  e) {
				 // count++;
				 // this->label1->Text="Time:"+Convert::ToString((float)count/10.0f);

				 current_time = clock();
				 this->label1->Text = String::Format("{0:0.00}", (float)(current_time - start_time) / CLOCKS_PER_SEC);
			 }

	private: System::Void buttonStop_Click(System::Object^  sender, System::EventArgs^  e) {
				 this->timer1->Stop();
			 }
	};
}