CustomButton
介绍：
自定义带波纹点击效果的Button，可以通过xml属性改变按钮外观，省去了写大量的<ripple/>xml文件，还可以支持代码动态设置，代码设置完成后需要调用use()方法。
用法：
app:shape="rectangle"   设置按钮的形状，矩形（包括带圆角的矩形）、圆形、线形、环形（环形一直显示不对，不知道怎么回事）
app:bgColor="@color/colorPrimary"   按钮可点击时的背景色
app:bgColorPress="@color/colorPrimaryDark"  按钮按下时的背景色
app:bgColorDisable="@color/white"   按钮不可用时的背景色
app:cornersRadius="20"      当shape为矩形时的圆角角度
app:strokeWidth="10"        边线宽
app:strokeColor="@color/black"      边线颜色
app:textColor="@color/white"        按钮正常状态时的文字颜色
app:textColorPress="@color/black"   按钮被按下时的文字颜色
app:textColorDisable="@color/gray"      按钮不可用时文字颜色
app:rippleColor="@color/colorAccent"       按钮被触摸时的波纹颜色
示例：
    xml方式
    <com.liuqiang.customviewlibrary.CustomButton
            android:id="@+id/customButton"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:text="自定义按钮"
            android:textColor="@color/white"
            android:textSize="20sp"
            android:gravity="center"
            //上面是按钮本身的属性，下面是自定义的按钮属性
            app:shape="rectangle"
            app:bgColor="@color/colorPrimary"
            app:bgColorPress="@color/colorPrimaryDark"
            app:bgColorDisable="@color/white"
            app:cornersRadius="20"
            app:strokeWidth="10"
            app:strokeColor="@color/black"
            app:textColor="@color/white"
            app:textColorPress="@color/black"
            app:textColorDisable="@color/gray"
            app:rippleColor="@color/colorAccent"
    />
    代码方式
    customButton_code = (CustomButton) findViewById(R.id.customButton1);
            customButton_code.setShapeType(CustomButton.RECTANGLE)
                    .setBgNormalColor(R.color.colorPrimary)
                    .setBgPressedColor(R.color.colorPrimaryDark)
                    .setBgDisableColor(R.color.white)
                    .setCornersRadius(20)
                    .setStrokeColor(R.color.black)
                    .setStrokeWidth(10)
                    .setTextNormalColor(R.color.white)
                    .setTextPressedColor(R.color.black)
                    .setTextDisableColor(R.color.gray)
                    .setRippleColor(R.color.colorAccent)
                    .use();
        }