package kantwonskids.donationtrackerg14b.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Model;
import kantwonskids.donationtrackerg14b.model.User;

import java.util.Collection;
import java.util.List;

public class AccountListActivity  extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        mRecyclerView = findViewById(R.id.account_list);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new MyAdapter(Model.getInstance().getUserList().values());
        mRecyclerView.setAdapter(mAdapter);

        // toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private List<User> mUsers;

        // Provide a reference to the views for each data item
        public class MyViewHolder extends RecyclerView.ViewHolder {
            View mTextView;
            TextView mContentView;
            User mUser;

            public MyViewHolder(View view) {
                super(view);
                mTextView = view;
                mContentView = view.findViewById(R.id.account_content_tv);
            }
        }

        public MyAdapter(List<User> users) {
            mUsers = users;
        }

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            // create a new view
            View view = layoutInflater.inflate(
                    R.layout.account_list_content, parent, false);
            return new AccountListActivity.MyAdapter.MyViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.mUser = mUsers.get(position);
            User mUser = mUsers.get(position);
            holder.mContentView.setText(mUser.toString());

            holder.mTextView.setOnClickListener( (View v) -> {
                Context context = v.getContext();
                holder.mUser.setLocked(!mUser.isLocked());
                holder.mContentView.setText(mUser.toString());
            });

        }

        @Override
        public int getItemCount() {
            return mUsers.size();
        }
    }
}