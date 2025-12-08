# L6 CSS**(第六部分)**

------

## **模块二十三：CSS 简介与核心概念**

### **1. 为什么要使用 CSS？**

- CSS 代表**层叠样式表 (Cascading Style Sheets)**。
- CSS 描述了 HTML 元素应该如何在屏幕、纸张或其他媒介上显示。
- CSS 能够节省大量工作，它可以一次性控制多个网页的布局。
- 外部样式表存储在 CSS 文件中。

**CSS 解决的一个大问题**：

- HTML 的初衷从来不是为了包含用于格式化网页的标签！HTML 的创建是为了描述网页的内容，例如 `<h1>` 和 `<p>`。
- 当像 `<font>` 这样的标签和颜色属性被添加到 HTML 3.2 规范中时，为大型网站的每个页面添加字体和颜色信息变成了一个漫长而昂贵的过程。
- 为了解决这个问题，万维网联盟 (W3C) 创建了 CSS。CSS 将样式格式化从 HTML 页面中移除！

------

### **2. CSS 语法**

- 一个 CSS 规则集由一个**选择器 (selector)** 和一个**声明块 (declaration block)** 组成。
- 选择器指向你想要设置样式的 HTML 元素。
- 声明块包含一条或多条由分号分隔的声明。
- 每条声明都包含一个 CSS **属性名 (property name)** 和一个**值 (value)**，由冒号分隔。
- 一条 CSS 声明总是以分号结束，声明块则由花括号包围。

------

### **3. CSS 选择器 (Selectors)**

CSS 选择器用于根据元素名称、id、类、属性等来“查找”（或选择）HTML 元素。

#### **元素选择器 (element Selector)**

- 元素选择器根据元素名称来选择元素。
- 例如，`p { ... }` 会选择页面上所有的 `<p>` 元素。

#### **id 选择器 (id Selector)**

- id 选择器使用 HTML 元素的 `id` 属性来选择一个特定的元素。
- 一个元素的 id 在页面中应该是唯一的，因此 id 选择器用于选择一个唯一的元素！
- 要选择一个具有特定 id 的元素，需要写一个哈希（#）字符，后跟该元素的 id。例如，`#para1 { ... }`。

#### **类选择器 (class Selector)**

- 类选择器选择具有特定 `class` 属性的元素。
- 要选择具有特定类的元素，需要写一个句点（.）字符，后跟类的名称。例如，`.center` 会选择所有 `class="center"` 的 HTML 元素。
- 你也可以指定只有特定的 HTML 元素受某个类的影响。例如，`p.center` 只会影响 `class="center"` 的 `<p>` 元素。
- HTML 元素还可以引用多个类。例如 `<p class="center large">`。
- **注意**: 类名不能以数字开头！

#### **分组选择器 (Grouping Selectors)**

- 如果有多个元素具有相同的样式定义，最好将选择器分组以最小化代码。
- 要分组选择器，用逗号分隔每个选择器。例如：`h1, h2, p { ... }`。

------

## **模块二十四：CSS 的应用方式**

### **1. 三种插入 CSS 的方式**

当浏览器读取样式表时，它将根据样式表中的信息来格式化 HTML 文档。有三种插入样式表的方式：

1. 外部样式表 (External style sheet)
2. 内部样式表 (Internal style sheet)
3. 内联样式 (Inline style)

#### **外部样式表 (External Style Sheet)**

- 每个页面必须在 `<head>` 部分内通过 `<link>` 元素包含对外部样式表文件的引用。

#### **内部样式表 (Internal Style Sheet)**

- 当单个页面具有独特的样式时，可以使用内部样式表。
- 内部样式在 HTML 页面的 `<head>` 部分内的 `<style>` 元素中定义。

#### **内联样式 (Inline Styles)**

- 当需要为单个元素应用独特样式时，可以使用内联样式。
- 要使用内联样式，需将 `style` 属性添加到相关元素中。`style` 属性可以包含任何 CSS 属性。

------

### **2. 多样式表与层叠顺序**

当为一个 HTML 元素指定了多个样式时，所有样式将根据以下规则“层叠”成一个新的“虚拟”样式表，其中序号 1 的优先级最高：

1. **内联样式** (在 HTML 元素内部)
2. **外部和内部样式表** (在 `<head>` 部分)
3. **浏览器默认样式**

因此，内联样式具有最高优先级，它将覆盖在 `<head>` 标签内、外部样式表或浏览器默认值中定义的样式。

------

## **模块二十五：CSS 核心属性**

### **1. CSS 颜色 (Colors)**

- 颜色是通过组合红 (RED)、绿 (GREEN) 和蓝 (BLUE) 光来显示的。
- 在 CSS 中，颜色最常通过以下方式指定：
  - 一个有效的颜色名称 - 如 `"red"`
  - 一个 RGB 值 - 如 `"rgb(255, 0, 0)"`
  - 一个 HEX 值 - 如 `"#ff0000"`

------

### **2. CSS 背景 (Backgrounds)**

CSS 背景属性用于定义元素的背景效果。

- **背景属性列表**: `background-color`, `background-image`, `background-repeat`, `background-attachment`, `background-position`

- **`background-color`**: 指定元素的背景颜色。

- **`background-image`**: 指定用作元素背景的图像。默认情况下，图像会在水平和垂直方向上重复，以覆盖整个元素。

- `background-repeat`

  :

  - `repeat-x`：使图像仅在水平方向上重复。
  - `no-repeat`：指定背景图像只显示一次。

- **`background-position`**: 可以改变背景图像的位置。

- `background-attachment`

  :

  - `fixed`：指定背景图像是固定的（不会随页面其余部分滚动）。

#### **背景简写属性 (Shorthand Property)**

- 为了缩短代码，可以将所有背景属性指定在一个单独的属性中，这个属性就是 `background`。
- 使用简写属性时，属性值的顺序是：`background-color`、`background-image`、`background-repeat`、`background-attachment`、`background-position`。
- 即使缺少某个属性值，只要其他值保持此顺序即可。

------

### **3. CSS 边框 (Borders)**

CSS 边框属性允许你指定元素边框的样式、宽度和颜色。

#### **`border-style`**

- 指定要显示的边框类型。
- 允许的值包括：`dotted`, `dashed`, `solid`, `double`, `groove`, `ridge`, `inset`, `outset`, `none`, `hidden`。
- **注意**: 除非设置了 `border-style` 属性，否则其他任何 CSS 边框属性都不会有任何效果！

#### **`border-width`**

- 指定四个边框的宽度。
- 宽度可以设置为具体尺寸（如 `px`, `pt`, `cm`, `em` 等）或使用三个预定义值之一：`thin`, `medium`, `thick`。

#### **`border-color`**

- 用于设置四个边框的颜色。
- 如果未设置 `border-color`，它将继承元素的颜色。

#### **单独边框设置**

- CSS 也提供了用于指定每个边框（上、右、下、左）的属性。

- ```
  border-style
  ```

   属性可以有一到四个值，分别对应上、右、下、左边框。值的数量决定了它们如何应用：

  - **四个值** (`dotted solid double dashed`): 分别应用于上、右、下、左。
  - **三个值** (`dotted solid double`): 上边框是 dotted，右和左边框是 solid，下边框是 double。
  - **两个值** (`dotted solid`): 上和下边框是 dotted，右和左边框是 solid。
  - **一个值** (`dotted`): 所有四个边框都是 dotted。

#### **边框简写属性 (`border`)**

- `border` 属性是 `border-width`, `border-style` (必需), 和 `border-color` 的简写属性。

------

### **4. CSS 外边距 (Margins)**

CSS 外边距属性用于在元素周围生成空间。外边距属性设置的是边框**外部**的空白区域大小。

#### **单独外边距属性**

- CSS 具有为元素每一侧指定外边距的属性：`margin-top`, `margin-right`, `margin-bottom`, `margin-left`。
- 所有外边距属性都可以有以下值：`auto`, `length` (px, pt, cm 等), `%`, `inherit`。
- **注意**: 外边距也可以使用负值来重叠内容。

#### **`auto` 值**

- 你可以将 `margin` 属性设置为 `auto`，以使元素在其容器内水平居中。
- 元素将占据指定的宽度，剩余空间将在左右外边距之间平均分配。

#### **外边距简写属性 (`margin`)**

- `margin` 属性是单独外边距属性的简写形式。
- **四个值** (`margin: 25px 50px 75px 100px;`): 分别是上、右、下、左外边距。
- **三个值** (`margin: 25px 50px 75px;`): 上外边距是 25px，右和左外边距是 50px，下外边距是 75px。
- **两个值** (`margin: 25px 50px;`): 上和下外边距是 25px，右和左外边距是 50px。
- **一个值** (`margin: 25px;`): 所有四个外边距都是 25px。