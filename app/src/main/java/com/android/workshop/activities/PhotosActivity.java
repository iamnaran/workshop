package com.android.workshop.activities;import androidx.annotation.NonNull;import androidx.recyclerview.widget.LinearLayoutManager;import androidx.recyclerview.widget.RecyclerView;import android.annotation.SuppressLint;import android.app.Activity;import android.os.Bundle;import android.util.DisplayMetrics;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ImageView;import android.widget.ProgressBar;import android.widget.RelativeLayout;import android.widget.TextView;import com.android.workshop.R;import com.android.workshop.helper.AppRecyclerViewAdapter;import com.android.workshop.helper.UpasargaActivity;import com.android.workshop.helper.UpasargaApplication;import com.android.workshop.helper.UpasargaToast;import com.android.workshop.models.Photos;import com.android.workshop.presenters.PhotosPresenter;import com.bumptech.glide.Glide;import java.util.ArrayList;import java.util.List;public class PhotosActivity extends UpasargaActivity implements PhotosPresenter.View {    private PhotosPresenter photosPresenter;    private RecyclerView recyclerView;    private ProgressBar progressBar;    private int VIEW_HEIGHT;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_photos);        initaliseView();        initaliseListeners();        VIEW_HEIGHT = containerHeightHeader(PhotosActivity.this);        getData();    }    public int containerHeightHeader(Activity activity) {        DisplayMetrics dm = new DisplayMetrics();        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);        double ratio = 2.5;        return (int) (dm.heightPixels / ratio);    }    @Override    public void initaliseView() {        recyclerView = findViewById(R.id.recycler_view);        progressBar = findViewById(R.id.progress_bar);    }    @Override    public void initaliseListeners() {        photosPresenter = new PhotosPresenter(this);    }    private void getData() {        progressBar.setVisibility(View.VISIBLE);        photosPresenter.getPhotos();    }    @Override    public void onResponseSuccess(List<Photos> photosList) {        //data from api        progressBar.setVisibility(View.GONE);        prepareRecyclerView(photosList);    }    @Override    public void onResponseFailure(String message) {        progressBar.setVisibility(View.GONE);        UpasargaToast.showLongToastWithMessage(message);    }    private void prepareRecyclerView(List<Photos> photosList) {        @SuppressLint("WrongConstant")        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PhotosActivity.this, LinearLayoutManager.VERTICAL, false);        PhotosRecyclerViewAdapter photosRecycelerViewAdapter = new PhotosRecyclerViewAdapter(photosList);        recyclerView.setLayoutManager(linearLayoutManager);        recyclerView.setAdapter(photosRecycelerViewAdapter);    }    public class PhotosRecyclerViewAdapter extends AppRecyclerViewAdapter {        private List<Photos> photosRecyclerList = new ArrayList<>();        public PhotosRecyclerViewAdapter(List<Photos> photosList) {            this.photosRecyclerList = photosList;        }        @Override        protected void add(Object object) {        }        @Override        protected void clear() {        }        @NonNull        @Override        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {            //layout            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photos_content, parent, false);            return new VHItem(itemView);        }        @Override        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {            //data            VHItem vhItem = (VHItem) holder;            vhItem.authorName.setText(photosRecyclerList.get(position).getAuthor());            Glide.with(UpasargaApplication.getAppContext()).load(photosRecyclerList.get(position).getDownloadUrl()).into(vhItem.imageView);            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, VIEW_HEIGHT);            layoutParams.setMargins(5, 5, 5, 5);            vhItem.container.setLayoutParams(layoutParams);        }        @Override        public int getItemCount() {            return photosRecyclerList.size();        }        private class VHItem extends RecyclerView.ViewHolder {            private TextView authorName;            private ImageView imageView;            private RelativeLayout container;            public VHItem(View itemView) {                super(itemView);                authorName = itemView.findViewById(R.id.author_name);                imageView = itemView.findViewById(R.id.image_view);                container = itemView.findViewById(R.id.container);            }        }    }}