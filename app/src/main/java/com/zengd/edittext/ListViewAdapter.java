package com.zengd.edittext;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.List;

/**
 * Created by zengd on 2016/8/17 0017.
 */
public class ListViewAdapter extends BaseAdapter {

    private List<ItemBean> mData;
    private Context mContext;

    public ListViewAdapter(Context mContext, List<ItemBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_edittext, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

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

        return convertView;
    }

    private class ViewHolder {
        private EditText editText;

        public ViewHolder(View convertView) {
            editText = (EditText) convertView.findViewById(R.id.edit_text);
        }
    }
}
