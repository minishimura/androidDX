package android.lifeistech.com.testex;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;

public class MemoAdapter extends ArrayAdapter<Memo> {

    public LayoutInflater layoutinflater;



    MemoAdapter(Context context, int textViewResourceId, List<Memo> objects){
        super(context,textViewResourceId,objects);
        layoutinflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent){

        Log.d("getView", String.valueOf(position));
        final Realm realm = Realm.getDefaultInstance();


        final Memo memo = getItem(position);
        if(convertView==null){
            convertView = layoutinflater.inflate(R.layout.layout_item_memo,null);
        }

        TextView titleText = (TextView)convertView.findViewById(R.id.titleText);
        final CheckBox ch = (CheckBox)convertView.findViewById(R.id.checkbox);
//        TextView contentText = (TextView)convertView.findViewById(R.id.contentText);
        titleText.setText(memo.title);
        //ch.setChecked(memo.check);

        //contentText.setText(memo.content);
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        if(ch.isChecked()==true){
                            memo.check = true;
                        }else{
                            memo.check = false;
                        }
                        //realm.copyToRealm(memo);
                    }
                });
            }

        });
        realm.close();

        return convertView;

    }
}
