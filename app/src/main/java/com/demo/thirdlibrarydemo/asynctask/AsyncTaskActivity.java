package com.demo.thirdlibrarydemo.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.demo.thirdlibrarydemo.R;

public class AsyncTaskActivity extends AppCompatActivity {

    private Button do_task;
    private ProgressBar pb;
    private MyAsyndTask mTask;

    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        do_task= (Button) findViewById(R.id.do_task);
        pb= (ProgressBar) findViewById(R.id.pb);

        do_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    flag=true;
                } else{
                    flag=false;

                    if (mTask!=null&&mTask.getStatus()==AsyncTask.Status.RUNNING){
                        mTask.cancel(true);//cancle()方法只是将AsyncTask标记为cancle状态，并不是真正的取消线程执行
                    }
                }
                mTask=new MyAsyndTask();
                mTask.execute();//必须在UI线程调用
            }
        });
    }

    /*
    *三种泛型类型分别代表:
    * 启动任务执行的输入参数  如url  String
    * 后台任务执行的进度    如返回进度条所需的int值
    * 后台计算结果的类型    如加载图片返回的Bitmap
    * 在特定场合下，并不是所有类型都被使用，如果没有被使用，可以用Java.lang.Void类型代替。
    *
    * 1.异步任务的实例必须在UI线程中创建。
      2.execute(Params... params)方法必须在UI线程中调用。
      3.不要手动调用onPreExecute()，doInBackground(Params... params)，onProgressUpdate(Progress... values)，onPostExecute(Result result)这几个方法。
      4.不能在doInBackground(Params... params)中更改UI组件的信息。
      5.一个任务实例只能执行一次，如果执行第二次将会抛出异常。
    *
    * */
    class MyAsyndTask extends AsyncTask<Void,Integer,Void>{
        /**
         * 用于执行后台操作前做一些UI操作
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        /**
         * doInBackground方法内部执行后台任务,不可在此方法内修改UI
         * @param params
         * @return
         */
        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 100; i++) {
                if (isCancelled()){
                    break;
                }
                publishProgress(i);//调用publishProgress公布进度,最后onProgressUpdate方法将被执行
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        /**
         * onProgressUpdate方法用于更新进度信息
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (isCancelled()){
                return;
            }
            //获取进度条更新值
            pb.setProgress(values[0]);
        }

        /**
         * onPostExecute方法用于在执行完后台任务后更新UI,显示结果
         * @param aVoid
         */
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            do_task.setText("完成");
            pb.setVisibility(View.GONE);
        }
    }
}
