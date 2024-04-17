package com.example.myinventotrack;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProvider;

public class UserListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.rvUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final UserAdapter adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);

        // Assuming UserViewModel is set up to retrieve users from the database
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, users -> {
            // Update the cached copy of the users in the adapter.
            adapter.setUsers(users);
        });
    }
}
