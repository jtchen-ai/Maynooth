# L10 JavaScript 

------

## **JavaScript 简介与核心功能**

JavaScript 是一门可以与 HTML 交互的脚本语言，具备多种动态修改网页内容的能力。

- **修改 HTML 内容**:
  - JavaScript 可以改变 HTML 元素的内部内容 (`innerHTML`)。
  - 通过使用 `getElementById()` 方法找到特定 `id` 的 HTML 元素，然后修改其 `innerHTML` 属性，可以更新页面上显示的文本。
- **修改 HTML 属性**:
  - JavaScript 能够改变 HTML 元素的属性。
  - 一个常见的例子是通过更改 `<img>` 标签的 `src` 属性来切换显示的图片。
- **修改 HTML 样式 (CSS)**:
  - 改变 HTML 元素的样式是修改 HTML 属性的一种变体。
  - 可以通过 JavaScript 访问元素的 `style` 对象，并修改其属性（如 `fontSize`、`color`）来动态更改元素的 CSS 样式。
- **数据验证**:
  - JavaScript 常常被用来验证用户的输入。
  - 可以获取输入字段的值，并使用条件语句（如 `if`）检查数据是否符合预设的规则（例如，是否为数字、是否在特定范围内）。

------

## **JavaScript 在 HTML 中的位置 (Where To)**

### **`<script>` 标签**

- 在 HTML 中，JavaScript 代码必须插入到 `<script>` 和 `</script>` 标签之间。
- 可以在一个 HTML 文档中放置任意数量的脚本。
- 脚本可以被放置在 HTML 页面的 `<body>` 部分、`<head>` 部分，或者同时存在于两处。

### **脚本位置**

- **在 `<head>` 中**: 可以在页面的 `<head>` 部分放置 JavaScript 函数。函数可以在之后被调用，例如当用户点击一个按钮时。
- **在 `<body>` 中**: 同样地，也可以在 `<body>` 部分放置 JavaScript 函数。当按钮被点击时，函数会被调用。

### **外部 JavaScript**

- 可以将脚本代码放置在外部文件中。

- 当同一个代码需要在多个不同的网页中使用时，使用外部脚本非常实用。

- JavaScript 文件的文件扩展名为 `.js`。

- 要使用外部脚本，需在 `<script>` 标签的 `src` (source) 属性中指定脚本文件的名称。

- 外部脚本的引用可以像内部脚本一样，放置在 `<head>` 或 `<body>` 中。脚本的行为就如同它被直接放在 `<script>` 标签所在的位置一样。

- 外部 JavaScript 的优势

  :

  - 它将 HTML 和代码分离开来。
  - 它使得 HTML 和 JavaScript 更易于阅读和维护。
  - 被缓存的 JavaScript 文件可以加快页面加载速度。

------

## **JavaScript 输出方法**

JavaScript 可以通过不同的方式“显示”数据：

- **`innerHTML`**:
  - 通过 `innerHTML` 属性将数据写入到一个 HTML 元素中。
  - 要访问一个 HTML 元素，JavaScript 可以使用 `document.getElementById(id)` 方法。`id` 属性定义了 HTML 元素，而 `innerHTML` 属性定义了其 HTML 内容。
- **`document.write()`**:
  - 使用 `document.write()` 将内容写入到 HTML 输出流中。
  - 为了测试目的，使用 `document.write()` 很方便。
  - **警告**: 在 HTML 文档完全加载后使用 `document.write()` 将会删除所有已存在的 HTML 内容。
- **`window.alert()`**:
  - 使用 `window.alert()` 方法通过警告框来显示数据。
- **`console.log()`**:
  - 使用 `console.log()` 方法在浏览器控制台中显示数据。
  - 可以使用 F12 键激活浏览器控制台，并在菜单中选择“Console”。

------

## **JavaScript 语法与结构**

### **语句 (Statements)**

- 在 HTML 中，JavaScript 语句是给浏览器的“命令”。
- JavaScript 代码（或简称 JavaScript）是一系列 JavaScript 语句的列表。
- 大多数 JavaScript 程序包含许多语句。这些语句会按照它们被书写的顺序，一个接一个地被执行。
- **分号 (`;`)**:
  - 分号用于分隔 JavaScript 语句。应在每个可执行语句的末尾添加分号。
  - 当用分号分隔时，允许多个语句存在于同一行。

### **代码块 (Code Blocks)**

- JavaScript 语句可以被组织在花括号 `{...}` 内的代码块中。
- 代码块的目的是定义需要一起执行的语句。
- 一个常见的使用场景是在 JavaScript 函数中将语句组合在代码块里。

### **其他语法要点**

- **空白 (White Space)**:
  - JavaScript 会忽略多余的空格。你可以在脚本中添加空白以使其更具可读性。
  - 一个好的实践是在操作符（`=` `+` `-` `*` `/`）周围加上空格。
- **关键字 (Keywords)**:
  - JavaScript 语句通常以一个关键字开始，用以识别要执行的 JavaScript 动作。
  - 例如 `var`, `function`, `if ... else`, `for` 等。
- **字符集 (Character Set)**:
  - JavaScript 使用 Unicode 字符集，它覆盖了世界上几乎所有的字符、标点和符号。

------

### **注释 (Comments)**

- **单行注释**:
  - 以 `//` 开始。
  - 任何在 `//` 和行尾之间的文本都会被 JavaScript 忽略（不会被执行）。
  - 可以用来在每行代码前或行末解释代码。
- **多行注释**:
  - 以 `/*` 开始，并以 `*/` 结束。
  - 任何在 `/*` 和 `*/` 之间的文本都会被 JavaScript 忽略。

------

## **JavaScript 变量与数据类型**

### **变量 (Variables)**

- **定义**: JavaScript 变量是用于存储数据值的容器。
- **声明**:
  - 在 JavaScript 中创建一个变量被称为“声明”变量。
  - 使用 `var` 关键字来声明一个 JavaScript 变量。
  - 可以在一个语句中声明多个变量，以 `var` 开始，并用逗号分隔变量。
- **赋值**:
  - 使用等号 (`=`) 为变量赋值。在 JavaScript 中，等号是“赋值”操作符，而不是“等于”操作符。
- **`undefined` 值**:
  - 一个声明后但没有赋值的变量，其值为 `undefined`。技术上它有一个 `undefined` 的值。
- **重新声明**:
  - 如果你重新声明一个 JavaScript 变量，它不会丢失其值。

### **标识符 (Identifiers)**

- **定义**: 所有的 JavaScript 变量都必须用唯一的名称来标识。这些唯一的名称被称为标识符。
- **命名规则**:
  - 名称可以包含字母、数字、下划线和美元符号。
  - 名称必须以字母开头。
  - 名称也可以用 `$` 和 `_` 开头。
  - 名称是大小写敏感的（例如，`y` 和 `Y` 是不同的变量）。
  - 保留字（如 JavaScript 关键字）不能用作名称。
- **驼峰式命名法 (Camel Case)**:
  - 将多个单词合并成一个变量名时，通常使用首字母小写的驼峰式命名法，如 `firstName`, `lastName`。

### **数据类型 (Data Types)**

- JavaScript 变量可以持有多种数据类型：数字、字符串、数组、对象等。
- **动态类型**: JavaScript 拥有动态类型，这意味着同一个变量可以被用作不同的类型。
- **主要类型**:
  - **字符串 (Strings)**:
    - 是一个字符序列，如 `"John Doe"`。
    - 字符串需要用引号书写，可以使用单引号或双引号。
    - 可以在一个字符串中使用引号，只要它们与包围字符串的引号不匹配即可。
  - **数字 (Numbers)**:
    - JavaScript 只有一种数字类型。
    - 数字可以带小数，也可以不带。
    - 极大或极小的数字可以用科学（指数）计数法书写。
  - **布尔 (Booleans)**:
    - 只能有两个值：`true` 或 `false`。
    - 常用于条件测试中。
  - **数组 (Arrays)**:
    - 用方括号 `[]` 书写。
    - 数组项之间用逗号分隔。
    - 数组索引是基于零的，第一个项目是 `[0]`，第二个是 `[1]`，以此类推。
  - **对象 (Objects)**:
    - 用花括号 `{}` 书写。
    - 对象属性以 `名称:值` 的对形式书写，并用逗号分隔。
- **`typeof` 操作符**: 可以使用 `typeof` 操作符来查找一个 JavaScript 变量的类型。
- **特殊值**:
  - **`undefined`**:
    - 一个没有值的变量，其值为 `undefined`，其 `typeof` 也是 `undefined`。
    - 可以通过将变量值设置为 `undefined` 来清空它。
  - **`null`**:
    - `null` 是 "nothing"，表示某个不存在的东西。
    - `null` 的数据类型是一个对象。
    - `undefined` 和 `null` 是有区别的，它们的值和类型都不同。
  - **空值 (Empty Values)**:
    - 一个空值与 `undefined` 无关。
    - 一个空的字符串变量既有值（`""`）也有类型（`string`）。

------

## **JavaScript 操作符**

- **算术操作符 (Arithmetic Operators)**:
  - `+`, `-`, `*`, `/`, `%`, `++`, `--`
- **赋值操作符 (Assignment Operators)**:
  - `=`, `+=`, `-=`, `*=`, `/=`, `%=`
- **字符串操作符 (String Operators)**:
  - `+` 用于连接字符串。
  - `+=` 也可用于连接字符串。
  - 字符串与数字相加：如果你将一个数字和一个字符串相加，结果将是一个字符串。表达式从左到右计算，顺序不同结果也不同。
- **比较与逻辑操作符 (Comparison and Logical Operators)**:
  - `==`, `===`, `!=`, `!==`, `>`, `<`, `>=`, `<=`, `?`（三元操作符）
- **类型操作符 (Type Operators)**:
  - `typeof`: 返回变量的类型。
  - `instanceof`: 判断对象是否为某个对象类型的实例。

------

## **JavaScript 函数 (Functions)**

- **定义**: 函数是执行特定任务的代码块。

- **执行**: 函数在被调用时执行，例如用户点击按钮时。

- **函数语法**:

  javascript

  复制

  ```
  function functionName(parameter1, parameter2, ...) {
    // 要执行的代码
  }
  ```

- **参数与实参**:

  - 参数是函数定义中的变量名。
  - 实参是调用函数时传入的值。
  - 实参在函数内部表现为局部变量。

- **返回值**:

  - `return` 语句用于返回函数结果并终止执行。

- **调用操作符 `()`**:

  - 使用 `()` 来调用函数。
  - 不带 `()` 表示函数本身，带 `()` 表示函数执行结果。

------

## **JavaScript 对象 (Objects)**

- **概念**: 对象是可以包含多个值的变量，以 `名称:值` 对存储。

- **属性**:

  - 对象中的 `名称:值` 对叫做属性。

  - 访问属性：

    javascript

    复制

    ```
    objectName.propertyName
    objectName["propertyName"]
    ```

- **方法**:

  - 方法是存储在属性中的函数。

  - 调用方法：

    javascript

    复制

    ```
    objectName.methodName()
    ```

  - 不带 `()` 访问方法会返回函数定义，而不是执行它。