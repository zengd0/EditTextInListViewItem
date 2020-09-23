## 安卓ListView的item中含有EditText，动态添加item时EditText值的保存

EditText是ListView的item，ListView的item可以动态添加，从而让用户动态输入一些内容。 如下图的需求：

![image](http://img.blog.csdn.net/20160818003431922)

要时刻保存EditText的值，我们需要给EditText设置一个文字改变时的监听器（addTextChangedListener），当文字发生改变后，我们获取EditText的值并存于itemObject的某个属性中。这里千万要脚下留心，在EditText调用setText之前，一定要把textChangedListener移除掉，否则setText后又会多次调用监听器里面的方法，造成值的清空。解决方法是每次getView时先remove掉监听器，再setText，最后再add监听器。
关键代码如下：
```
final ItemBean itemObj = mData.get(position);

        //This is important. Remove TextWatcher first.
        if (holder.editText.getTag() instanceof TextWatcher) {
            holder.editText.removeTextChangedListener((TextWatcher) holder.editText.getTag());
        }

        holder.editText.setText(itemObj.getText());

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    itemObj.setText("");
                } else {
                    itemObj.setText(s.toString());
                }
            }
        };

        holder.editText.addTextChangedListener(watcher);
        holder.editText.setTag(watcher);
```

由于我的实际项目中item里有三个EditText，在这里我简化成只有一个EditText。再多个EditText也是同理实现的。这个Demo的运行效果如图：

![image](http://img.blog.csdn.net/20160818004635058)

查看更详细的描述请访问：

http://blog.csdn.net/zengd0/article/details/52236158
