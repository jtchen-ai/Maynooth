# **Web 信息处理课程学习资料 (第五部分)**

------

## **模块二十二：HTML 元数据与编码约定**

### **1. 元数据 (Meta Data)**

- 在 HTML5 中，`<title>` 元素是必需的。标题应尽可能有意义。
- 为了确保正确的解析和搜索引擎索引，应尽早在文档中定义语言和字符编码。

### **2. HTML 注释**

- 简短的注释应该写在一行上，`<!-- -->` 之前应有一个空格。
- 跨越多行的长注释，`<!--` 和 `-->` 应分别写在独立的行上。
- 如果长注释缩进 2 个空格，会更容易观察。

### **3. 样式表 (Style Sheets)**

- 链接样式表时使用简单的语法（`type` 属性不是必需的）。
- 简短的 CSS 规则可以压缩写在一行。
- 长的 CSS 规则应该分成多行来写。

### **4. 加载 JavaScript**

- 加载外部脚本时使用简单的语法（`type` 属性不是必需的）。

### **5. 文件命名与扩展名**

#### 文件名大小写

- 推荐始终使用小写文件名（如果可能）。
- 许多 Web 服务器（如 Apache, Unix）对文件名是大小写敏感的。例如，`london.jpg` 不能通过 `London.jpg` 访问。
- 其他服务器（如 Microsoft, IIS）则不区分大小写。
- 如果你混合使用大小写，就必须保持高度一致。
- 从不区分大小写的服务器迁移到区分大小写的服务器时，即使是微小的错误也会破坏你的网站。

#### 文件扩展名

- HTML 文件应使用 `.html`（或 `.htm`）扩展名。
- CSS 文件应使用 `.css` 扩展名。
- JavaScript 文件应使用 `.js` 扩展名。

#### .htm 与 .html 的区别

- `.htm` 和 `.html` 扩展名之间没有区别。任何 Web 浏览器或服务器都会将两者视作 HTML。
- 区别在于文化层面：`.htm` 源于早期 DOS 系统限制扩展名为 3 个字符，而 `.html` 源于没有此限制的 Unix 操作系统。
- 技术上，当 URL 未指定文件名时，服务器会返回一个默认文件。常见的默认文件名是 `index.html`, `index.htm` 等。如果你的服务器只配置了 `index.html` 作为默认文件，那么你的文件必须命名为 `index.html`。
- 总之，HTML 文件的完整扩展名是 `.html`，没有理由不使用它。

------

## **模块二十三：HTML5 Canvas**

### **1. 什么是 Canvas?**

- HTML `<canvas>` 元素用于在网页上绘制图形。
- `<canvas>` 元素用于通过脚本（通常是 JavaScript）动态地绘制图形。
- `<canvas>` 元素本身只是一个图形容器，你必须使用脚本来实际绘制图形。
- Canvas 提供了多种方法用于绘制路径、方框、圆形、文本和添加图像。

### **2. Canvas 示例与用法**

- Canvas 是 HTML 页面上的一个矩形区域。默认情况下，它没有边框也没有内容。

#### 基本语法

- 务必为 canvas 指定一个 `id` 属性（以便在脚本中引用），以及 `width` 和 `height` 属性来定义其大小。
- 可以使用 `style` 属性为其添加边框。

#### 使用 JavaScript 绘图

- **绘制矩形**: `ctx.fillRect(x, y, width, height)`

- 绘制线条

  :

  - `moveTo(x, y)`: 将路径移动到画布中的指定点，不创建线条。
  - `lineTo(x, y)`: 添加一个新点，并从该点到上一个指定点创建一条线。
  - `stroke()`: 实际绘制已定义的路径。
  - `beginPath()`: 开始一个新路径，或重置当前路径。

- **绘制圆形**: `ctx.arc(x, y, r, sAngle, eAngle)`

- 绘制文本

  :

  - `fillText(text, x, y)`: 绘制实心文本。
  - `strokeText(text, x, y)`: 绘制空心文本。

- **绘制图像**: `ctx.drawImage(img, x, y)`

------

## **模块二十四：HTML5 多媒体**

### **1. 什么是多媒体?**

- 多媒体有多种不同格式，几乎可以是你能听到或看到的任何东西。例如图片、音乐、声音、视频、动画等。
- 多媒体元素存储在媒体文件中，通常通过文件扩展名来识别其类型，如 `.mp3`, `.mp4`, `.wav` 等。
- 在 HTML5 出现之前，播放视频和音频没有统一标准，通常需要插件（如 Flash）。

### **2. HTML5 视频 (`<video>`)**

- HTML5 `<video>` 元素提供了一种在网页中嵌入视频的标准方式。

#### 用法

- `controls` 属性会添加视频控件，如播放、暂停和音量。
- 推荐始终包含 `width` 和 `height` 属性，否则在视频加载时页面会发生变化或闪烁。
- `<video>` 和 `</video>` 标签之间的文本只会在不支持 `<video>` 元素的浏览器中显示。
- 可以提供多个 `<source>` 元素链接到不同的视频文件，浏览器将使用第一个可识别的格式。

#### 自动播放

- 使用 `autoplay` 属性可以使视频自动播放。注意：此属性在 iPad 和 iPhone 等移动设备上不起作用。
- **支持的格式**: 目前 `<video>` 元素支持三种视频格式：MP4, WebM, 和 Ogg。
- **DOM 操作**: HTML5 为 `<video>` 元素定义了 DOM 方法、属性和事件，允许通过脚本加载、播放、暂停视频以及设置时长和音量等。

### **3. HTML5 音频 (`<audio>`)**

- HTML5 `<audio>` 元素提供了一种在网页中嵌入音频的标准方式。

#### 用法

- `controls` 属性会添加音频控件，如播放、暂停和音量。
- `<audio>` 和 `</audio>` 标签之间的文本会在不支持 `<audio>` 元素的浏览器中显示。
- 同样可以使用多个 `<source>` 元素，浏览器将使用第一个可识别的格式。
- **支持的格式**: 目前 `<audio>` 元素支持三种文件格式：MP3, Wav, 和 Ogg。
- **DOM 操作**: HTML5 也为 `<audio>` 元素定义了 DOM 方法、属性和事件，允许通过脚本进行控制。

------

## **模块二十五：HTML5 API**

### **1. 地理定位 (Geolocation)**

- **定义**: HTML 地理定位 API 用于获取用户的地理位置。由于涉及隐私，除非用户批准，否则位置信息不可用。

#### 使用方法

- `getCurrentPosition()` 方法用于获取用户的当前位置。
- 如果成功，该方法会返回一个坐标对象给指定的回调函数（例如 `showPosition`）。

#### 返回数据

- 总是返回纬度 (`coords.latitude`)、经度 (`coords.longitude`) 和精度 (`coords.accuracy`) 属性。其他属性如海拔、方向、速度等在可用时返回。

#### 错误处理

- `getCurrentPosition()` 的第二个参数是一个函数，用于处理获取位置失败时的错误。

#### 其他方法

- `watchPosition()`: 返回用户当前位置，并随着用户移动持续返回更新后的位置。
- `clearWatch()`: 停止 `watchPosition()` 方法。
- **用途**: 地理定位对于提供位置相关的特定信息非常有用，例如本地信息、显示用户附近的兴趣点或导航。

### **2. 拖放 (Drag and Drop)**

- **定义**: 拖放是一种常见功能，指“抓住”一个对象并将其拖到不同位置。在 HTML5 中，任何元素都可以被拖放。

#### 实现步骤

1. **使元素可拖动**: 将元素的 `draggable` 属性设置为 `true`。

2. **指定拖动内容 (`ondragstart`)**: `ondragstart` 属性调用一个函数，该函数使用 `dataTransfer.setData()` 方法设置被拖动数据的类型和值。

3. **指定放置区域 (`ondragover`)**: `ondragover` 事件指定了可以放置被拖动数据的位置。必须调用 `event.preventDefault()` 方法来允许放置，因为默认情况下元素不能被放置在其他元素中。

4. 执行放置 (`ondrop`)

   :

   - 调用 `event.preventDefault()` 来阻止浏览器的默认处理（如作为链接打开）。
   - 使用 `dataTransfer.getData()` 方法获取被拖动的数据。
   - 将被拖动的元素附加到放置目标元素中。

### **3. 本地存储 (Local Storage)**

- **定义**: 通过本地存储，Web 应用程序可以在用户的浏览器内本地存储数据。
- **与 Cookies 的区别**: 本地存储更安全，可以存储大量数据（至少5MB）而不会影响网站性能，并且信息永远不会传输到服务器。

#### 存储对象

- `window.localStorage`: 存储没有过期日期的数据。浏览器关闭后数据不会被删除。
- `window.sessionStorage`: 为单个会话存储数据。当用户关闭特定的浏览器标签页时，数据将被删除。

#### 使用方法

- 在使用前，应检查浏览器是否支持 `localStorage` 和 `sessionStorage`。

- 存储数据:

  javascript

  复制

  ```
  localStorage.setItem("key", "value");
  // 或者
  localStorage.key = "value";
  ```

- 检索数据:

  javascript

  复制

  ```
  localStorage.getItem("key");
  // 或者
  localStorage.key;
  ```

- 删除数据:

  javascript

  复制

  ```
  localStorage.removeItem("key");
  ```

- **注意**: 名称/值对始终作为字符串存储，需要时记得转换格式。

### **4. Web Workers**

- **定义**: Web Worker 是一个在后台独立于其他脚本运行的 JavaScript，不会影响页面的性能。当它在后台运行时，你仍然可以与页面进行交互。

#### 使用方法

1. **检查支持**: 在创建前，先检查浏览器是否支持 Web Worker。
2. **创建 Worker 文件**: 在一个外部 JavaScript 文件中编写 Worker 脚本。该脚本使用 `postMessage()` 方法将消息发送回 HTML 页面。
3. **创建 Worker 对象**: 在主页面中，使用 `new Worker("your_worker_file.js")` 创建一个 Web Worker 对象。
4. **通信**: 为 Worker 对象添加 `onmessage` 事件监听器来接收来自 Worker 的消息。Worker 发送的数据存储在 `event.data` 中。

- **终止 Worker**: 使用 `terminate()` 方法来终止一个 Web Worker 并释放资源。
- **限制**: 由于 Web Worker 在外部文件中，它们无法访问 `window` 对象、`document` 对象和 `parent` 对象。