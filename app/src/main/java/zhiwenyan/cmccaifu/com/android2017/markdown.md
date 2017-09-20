本文只是Markdown的常用基础语法整理，让你能够快速使用markdown，如果需要使用更高级的功能，请参考markdown语法说明。

标题

支持两种标题的语法，类 Setext和类 atx 形式。

Setext 用底线的形式，利用 =（最高阶标题）和 -（第二阶标题），= 和- 没有数量限制，>=2即可。
使用Setext的方式1
=====
使用Setext的方式2
-----------
Atx是在行首插入 1 到 6 个 #，对应到标题 1 到 6 阶,1阶最大，6阶最小
#  H1标题
##  H2标题
###  H3标题
####  H4标题
#####  H5标题
######  H6标题
区块引用

使用 > 作为引用符号。在每行前添加>例如:
> Android 4.3 builds on the performance improvements already included in Jelly
> Bean — **vsync timing**, **triple buffering**, **reduced touch latency**, **CPU
> input boost**, and **hardware-accelerated 2D rendering** — and adds new
> optimizations that make Android even faster.

或者只在整个段落的第一行最前面加上 >，例如：
> For highest-performance graphics, Android 4.3 introduces support for **OpenGL ES 3.0** and makes it accessible to apps through both framework and native APIs. On supported devices, the hardware accelerated 2D rendering engine takes advantage of OpenGL ES 3.0 to optimize **texture management**and increase **gradient rendering fidelity**.

列表

无序列表 使用星号（*）、加号（+）或是减号（-）作为列表标记，例如：
* Android
* IOS
* WP

+ Android
+ IOS
+ WP

- Android
- IOS
- WP

2.有序列表 数字接着一个英文句点,数字没有顺序要求，例如：
1. Android
2. IOS
3. WP
或者
10. Android
35. IOS
4. WP
结果都会显示成

Android
IOS
WP
代码区块

Markdown 中建立代码区块很简单，只要 缩进 4 个空格 或是 1 个制表符就可以，例如：

Hello，这是一个代码区块。
一个代码区块会一直持续到没有缩进的那一行。

分隔线

在一行中用三个以上的星号（*）、减号（-）、底线（_）,该行内不能再有其他的东西，例如;

* * *
***
********
- - -
_ _ _ _

超链接

行内式写法
格式：方块括号后面紧接着圆括号并插入网址链接,如果你还想要加上链接的 title 文字，只要在网址后面，用双引号把 title 文字包起来即可
[超链接文字]( 超链接Url "链接的 title 文字")
例如：
[Android Developers](https://developer.android.com/index.html)
参考式
格式：
[超链接文字][链接的标记]
[链接的标记]: https://developer.android.com/index.html "Developers"
这个写法个人感觉没有上一个方便，平时基本行内式就够了，这里就不在多说。
强调

使用星号（*）和底线（_）作为标记强调字词的符号。例如：

只有一个*或者_的，显示斜体效果

*Android Studio*

_Google Developers _

结果
Android Studio

Google Developers

两个*或者_的，显示加粗效果：

**Android Studio**

__Google Developers__

结果
Android Studio

Google Developers

代码

标记一小段行内代码，用反引号（`）把它包起来即可，例如：

`private ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();`

图片

行内式
格式：一个惊叹号+一个方括号，里面放上图片的替代文字+一个普通括号，里面放上图片的网址
![图片的替代文字](图片的网址) 例如：
![0.gif](http://upload-images.jianshu.io/upload_images/1179815-886f0e2250cfb959.gif?imageMogr2/auto-orient/strip)
显示为：

参考式
格式：![Alt text][id]
和超链接相似，不常用。
反斜杠

Markdown 支持以下这些符号前面加上反斜杠来帮助插入普通的符号：
\ 反斜线
` 反引号
* 星号
_ 底线
{} 花括号
[] 方括号
() 括弧
# 井字号
+ 加号
- 减号
. 英文句点
! 惊叹号

补充

首行空格
由于markdown不负责实现段首缩进，所以只能手动输入空格，实现首行空格主要有一下几种写法：
&nbsp;
&emsp;
全角空格,切换到全角模式下（一般的中文输入法都是按 shift + space）输入两个空格即可。
推荐使用第三种方式，简单高效。

作者：Knight_Davion
链接：http://www.jianshu.com/p/961cfd1b6a69
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。