package com.example.rahul.hit.createcomplaint.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.hit.BaseActivity;
import com.example.rahul.hit.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddCreateComplaint extends BaseActivity {

    private static final String TAG = "AddCreateComplaint";
    public String imageURLSaveComplaint;


    @BindView(R.id.textLayout_Title_AddComplaintPage)
    TextInputLayout titleTextInputLayout;

    @BindView(R.id.textLayout_Description_AddComplaintPage)
    TextInputLayout descriptionTextInputLayout;

    @BindView(R.id.editText_Description_AddComplaintPage)
    EditText ComplaintDescription;

    @BindView(R.id.editText_Title_AddComplaintPage)
    EditText ComplaintTitle;

    @BindView(R.id.radioButton_AddCreateComplaintPage_LowPriority)
    RadioButton lowRadioButton;

    @BindView(R.id.radioButton_AddCreateComplaintPage_MediumPriority)
    RadioButton mediumRadioButton;

    @BindView(R.id.radioButton_AddCreateComplaintPage_HighPriority)
    RadioButton highRadioButton;

    Context context;

    public String mCurrentPhotoPath;

    @BindView(R.id.imageView_AddComplaint_DispComplaintImage)
    ImageView complaintImage;


    @BindView(R.id.button_AddComplaint_Upload)
    Button uploadImage;

    @BindView(R.id.button_AddComplaint_Save)
    Button SaveComplaint;

    @BindView(R.id.radioGroup_AddComplaint)
    RadioGroup radioGroup;


    //Earlier used to perform camera actions

    /*private static final int CAMERA_REQUEST_CODE = 1;
    private static final int GALLERY_INTENT = 2;
    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 0;*/


    public static final int CAMERA_PERMISSION_REQUEST = 10;

    public static final int PICK_IMAGE_GALLERY_REQUEST = 0;
    public static final int PICK_IMAGE_CAMERA_REQUEST = 1;

    Uri uri;

    DatabaseReference ComplaintDatabase;
    DatabaseReference mStorageDatabase;
    StorageReference storageReference;

    CoordinatorLayout coordinatorLayout;

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();


    private String mDefectImageUrl;
    private String priority = "";
    double progressfile;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_create_complaint);

        progressBar = findViewById(R.id.progressBar);

        //FirebaseApp.initializeApp(this);
        mStorageDatabase = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        ButterKnife.bind(this);

        context = this;
        toolbar = findViewById(R.id.create_complaint_toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#303F9F"));
        }

        toolbar.setNavigationIcon(R.drawable.back_arrow);
        toolbar.setTitle(R.string.add_create_complaint);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //SaveComplaint.setEnabled(false);


        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UploadImage();
                /*Intent selectImageIntent=new Intent(Intent.ACTION_PICK);

                //Below line is commented while performing alternate method for Capturing image, upload image and Retrive image
                selectImageIntent.setType("image/*");

                startActivityForResult(Intent.createChooser(selectImageIntent,"Select Image"),PICK_IMAGE_GALLERY_REQUEST);*/

                ImageOptionSelection();
            }
        });

    }

    @Override
    protected void init() {

    }

    @OnClick(R.id.button_AddComplaint_Save)
    public void saveComplaint() {
        final String Title = ComplaintTitle.getText().toString();
        final String Description = ComplaintDescription.getText().toString();
        final String imageURL = imageURLSaveComplaint;
        final String id = String.valueOf(System.currentTimeMillis());
        final String status = "Open";
        Log.d(TAG, "" + imageURL);
        RadioButton SelectPriority = findViewById(radioGroup.getCheckedRadioButtonId());
        priority = SelectPriority.getText().toString();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                switch (i) {
                    case R.id.radioButton_AddCreateComplaintPage_LowPriority:
                        priority = "Low";
                        break;
                    case R.id.radioButton_AddCreateComplaintPage_MediumPriority:
                        priority = "Medium";
                        break;
                    case R.id.radioButton_AddCreateComplaintPage_HighPriority:
                        priority = "High";
                        break;
                }
            }
        });


        Log.d(TAG, "saveComplaint: Inside savecomplaint" + imageURL);
        /*if (imageURL.isEmpty()){
            Toast.makeText(context, "Please attach image", Toast.LENGTH_SHORT).show();
        }*/
        if (TextUtils.isEmpty(Title)) {
            titleTextInputLayout.setError("Title is empty");
            return;
            //Toast.makeText(this, "Title is empty", Toast.LENGTH_SHORT).show();

        }
        if (TextUtils.isEmpty(Description)) {
            descriptionTextInputLayout.setError("Description is empty");
            return;
            //Toast.makeText(this, "Description is empty", Toast.LENGTH_SHORT).show();
        }

        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateSaveButton();
            }
        };

        ComplaintTitle.addTextChangedListener(tw);
        ComplaintDescription.addTextChangedListener(tw);
        /*if(ComplaintTitle.getText()!=null && ComplaintDescription.getText()!=null){

        }*/

        SaveComplaint.setEnabled(ComplaintTitle.getText() != null && ComplaintDescription.getText() != null);

        final String email = "_" + baseActivityPreferenceHelper.getString("mail", "");
        Log.d(TAG, "In add create complaint email value is " + email);

        ComplaintDatabase = mStorageDatabase.child("Create Complaint");
        //ComplaintDatabase.child(String.valueOf(System.currentTimeMillis())+email.substring(0,email.indexOf("@")));

        ComplaintDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final CreateComplaintModel createComplaintModel = new CreateComplaintModel(Title, Description, priority, imageURL, id, status);
                //mStorageDatabase.child("Create Complaint").child(""+count+email.substring(0,email.indexOf("@"))).setValue(createComplaintModel);
                ComplaintDatabase.child(String.valueOf(id)
                        + email.substring(0, email.indexOf("@"))).setValue(createComplaintModel);
                Toast.makeText(context, "Complaint added..", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        finish();
    }

    private void updateSaveButton() {
        SaveComplaint.setEnabled(ComplaintTitle.getText().length() > 0 && ComplaintDescription.getText().length() > 0);
    }

    @OnClick({R.id.radioButton_AddCreateComplaintPage_LowPriority,
            R.id.radioButton_AddCreateComplaintPage_MediumPriority,
            R.id.radioButton_AddCreateComplaintPage_HighPriority})
    public void radioButtonClicked(View view) {
        int radioButtonView = view.getId();
        if (radioButtonView == R.id.radioButton_AddCreateComplaintPage_LowPriority) {
            Toast.makeText(this, "Low", Toast.LENGTH_SHORT).show();
        }
        if (radioButtonView == R.id.radioButton_AddCreateComplaintPage_MediumPriority) {
            Toast.makeText(this, "Medium", Toast.LENGTH_SHORT).show();
        }
        if (radioButtonView == R.id.radioButton_AddCreateComplaintPage_HighPriority) {
            Toast.makeText(this, "High", Toast.LENGTH_SHORT).show();
        }
    }

    public void ImageOptionSelection() {

        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ADD IMAGE");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which].equals("Camera")) {

                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                            == PackageManager.PERMISSION_DENIED) {
                        ActivityCompat.requestPermissions(AddCreateComplaint.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST);
                    } else {
                        Log.d(TAG, "In else of camera dialog");
                        openCameraIntent();
                    }
                }
                if (items[which].equals("Gallery")) {
                    Intent imageIntent = new Intent();
                    imageIntent.setType("image/*");
                    imageIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(imageIntent, "Select File"), PICK_IMAGE_GALLERY_REQUEST);
                }
                if (items[which].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    public void openCameraIntent() {
        Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Log.d(TAG, "before photo null declaration");
        File photoFile = null;
        try {
            Log.d(TAG, "inside try of phorofile");
            photoFile = createImageFile();
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        getApplicationContext().getPackageName() + ".fileprovider",
                        photoFile);
                imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(imageIntent, PICK_IMAGE_CAMERA_REQUEST);
            }
        } catch (Exception ex) {
            Log.d(TAG, "Exception While Creating ImageFile : " + ex.toString());
        }
        //startActivityForResult(imageIntent, CAMERA_PERMISSION_REQUEST);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        Log.d(TAG, "before timestamp");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        Log.d(TAG, "After timestamp");
        String imageFileName = "JPEG_" + timeStamp + "_";
        Log.d(TAG, " " + imageFileName);
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpeg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PICK_IMAGE_CAMERA_REQUEST:
                    Log.d(TAG, "inside camera Request : " + data);
                    if (data != null && data.getData() != null) {
                        Uri uri = data.getData();

                        /*final StorageReference filepath=storageReference.child("Photos").child(uri.getLastPathSegment());
                        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        mDefectImageUrl=uri.toString();
                                        imageURLSaveComplaint=mDefectImageUrl;
                                        Log.d(TAG, "onSuccess: "+imageURLSaveComplaint);
                                    }
                                });
                            }
                        });*/
                        //Log.d(TAG,"inside if: "+STURL);

                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            // Log.d(TAG, String.valueOf(bitmap));

                            complaintImage.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            File file = new File(mCurrentPhotoPath);
                            Uri uri = FileProvider.getUriForFile(this,
                                    getApplicationContext().getPackageName() + ".fileprovider",
                                    file);
                            final StorageReference filepath = storageReference.child("Photos").child(uri.getLastPathSegment());
                            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            mDefectImageUrl = uri.toString();
                                            imageURLSaveComplaint = mDefectImageUrl;
                                            Log.d(TAG, "onSuccess: " + imageURLSaveComplaint);
                                        }
                                    });
                                }
                            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    double progressfile = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                    progressBar.setProgress((int) progressfile);
                                    if (progressfile == 100) {
                                        progressBar.setVisibility(View.VISIBLE);
                                        SaveComplaint.setVisibility(View.VISIBLE);
                                        SaveComplaint.setEnabled(true);
                                        SaveComplaint.setClickable(true);

                                    }
                                    Log.d(TAG, "onProgress: " + taskSnapshot.getBytesTransferred());

                                }
                            });
                            //Log.d(TAG,"Inside else: "+STURL);
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            // Log.d(TAG, String.valueOf(bitmap));
                            /*complaintImage.setMaxHeight(200);
                            complaintImage.setMaxWidth(370);*/
                            complaintImage.setImageBitmap(bitmap);

                        } catch (Exception e) {
                            Log.d(TAG, "Exception inside PICK_IMAGE_CAMERA_REQUEST : " + e.toString());
                        }
                    }
                    break;

                case PICK_IMAGE_GALLERY_REQUEST:
                    Log.d(TAG, "inside Gallery Request : " + data);
                    if (data != null) {
                        Uri uri = data.getData();

                        final StorageReference filepath = storageReference.child("Photos").child(uri.getLastPathSegment());
                        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        mDefectImageUrl = uri.toString();
                                        imageURLSaveComplaint = mDefectImageUrl;
                                        Log.d(TAG, "onSuccess: " + imageURLSaveComplaint);
                                    }
                                });
                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                progressBar.setVisibility(View.VISIBLE);
                                progressfile = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                progressBar.setProgress((int) progressfile);
                                if (progressfile == 100) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    SaveComplaint.setVisibility(View.VISIBLE);
                                    SaveComplaint.setClickable(true);
                                    SaveComplaint.setEnabled(true);
                                    Toast.makeText(context, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        //Log.d(TAG,"inside Gallery: "+STURL);
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            // Log.d(TAG, String.valueOf(bitmap));

                            /*new Thread(new Runnable() {
                                public void run() {
                                    while (progressStatus < 100) {
                                        progressStatus += 1;
                                        // Update the progress bar and display the
                                        //current value in the text view
                                        handler.post(new Runnable() {
                                            public void run() {
                                                progressBar.setProgress(progressStatus);
                                                //textView.setText(progressStatus+"/"+progressBar.getMax());
                                            }
                                        });
                                        try {
                                            // Sleep for 200 milliseconds.
                                            Thread.sleep(200);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }).start();*/
                            /*complaintImage.setMaxHeight(200);
                            complaintImage.setMaxWidth(370);*/

                            complaintImage.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            openCameraIntent();
        } else {
            Toast.makeText(this, "camera permission not Granted", Toast.LENGTH_LONG).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    //This mehtod is working and it consits of selecting image from gallery or file manager. Part 13 is refered on Youtube
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_INTENT && resultCode==RESULT_OK && data != null && data.getData()!=null ){
            final Uri uri= data.getData();
            final StorageReference filePath= storageReference.child("Photos").child(uri.getLastPathSegment());

            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Log.d("URI", "onSuccess: uri= "+ uri.toString());
                        }
                    });
                    Toast.makeText(AddCreateComplaint.this, "Done", Toast.LENGTH_SHORT).show();
                    Picasso.get().load(uri).into(complaintImage);
                }
            });
        }
    }*/


    // This method consists of capturing image . This is referred from part-14 V.C.:Youtube
    //THis mehtod is not working, the image is not displYING after the selcection
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CAMERA_REQUEST_CODE && resultCode==RESULT_OK && data!= null && data.getData()!=null){
            final Uri uri=data.getData();
            final StorageReference filePath=storageReference.child("Photos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Picasso.get().load(uri).into(complaintImage);
                    Toast.makeText(AddCreateComplaint.this,"Photo Uploaded to DB",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }*/


    //Third method soncists of all functions
    /*private void UploadImage() {

        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ADD IMAGE");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which].equals("Camera")) {
                    Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(imageIntent, REQUEST_CAMERA);
                }
                if (items[which].equals("Gallery")) {
                    Intent imageIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    imageIntent.setType("image/*");
                    startActivityForResult(Intent.createChooser(imageIntent, "Select File"), SELECT_FILE);
                }
                if (items[which].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }*/

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == REQUEST_CAMERA) {

                    if (uri != null) {
                        final StorageReference filepath = storageReference.child("Photos");
                        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.d("Progress", "prog");
                                    }
                                }, 500);

                                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        //Upload upload = new Upload(imgDescription.getText().toString().trim(), uri.toString());
                                        String uploadID = mStorageDatabase.child("Photos").push().getKey();
                                        mStorageDatabase.child(uploadID).setValue("dj");
                                        Picasso.get().load(uri).into(complaintImage);

                                        Toast.makeText(AddCreateComplaint.this, "Upload successfully", Toast.LENGTH_LONG).show();
                                    }
                                });

                            }
                        });
                    }
                    final Uri uri = data.getData();
                    StorageReference filePath = storageReference.child("Photos");

                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    complaintImage.setImageBitmap(bitmap);

                    filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(AddCreateComplaint.this, "Done", Toast.LENGTH_SHORT).show();
                            Picasso.get().load(uri).into(complaintImage);
                        }
                    });

                    Picasso.get().load(uri).into(complaintImage);
                } else if (requestCode == SELECT_FILE) {
                    final Uri uri = data.getData();
                    //complaintImage.setImageURI(uri);
                    StorageReference filePath = storageReference.child("Photos").child(uri.getLastPathSegment());
                    filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(AddCreateComplaint.this, "Done", Toast.LENGTH_SHORT).show();
                            Picasso.get().load(uri).into(complaintImage);
                        }
                    });
                    //Picasso.get().load(uri).into(complaintImage);
                }
            }
        }
    }

*/
}
