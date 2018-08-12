# Android6.0+ 运行时权限申请 工具类

**RequestPermissionUtil.kt** 是一个kotlin写的运行时权限工具类

封装了如下方法

1. 申请权限，返回值为是否有权限，回调方法为是否需要解释权限用途

```request(activity: Activity, permissions: Array<String>, requestCodes: Array<Int>,@Nullable descriptions: Array<String>,@Nullable showDescriptionsListener: ((activity: Activity, permission: String,shouldShowRequestPermissionRationale: Boolean, requestCode: Int, description: String) -> Unit)?): Boolean {}```

2. 申请权限结果回调方法，回调方法为申请权限是成功

```onRequestPermissionsResult(activity: Activity, requestCode: Int,  permissions: Array<String>?, @NonNull grantResults: IntArray?,@Nullable descriptions: Array<String>,@Nullable listener: ((Activity, String?, Boolean, Int, String) -> Unit)?) {}```

3. 展示弹出解释权限用途，请求权限等等，可以自己写其他实现

```showDialog(activity: Activity, permission: String, requestCode: Int, description: String,@Nullable onButtonClickListener: ((whichButton:Int,activity: Activity, permission: String, requestCode: Int, description: String) -> Unit)?) {}```



以下分别是其在Kotlin和Java中的用法实例

* RequestPermissionkotlinDemo.kt
* RequestPermissionJavaDemo.java