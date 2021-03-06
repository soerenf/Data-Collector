package d.sfischer.datacollector;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

//import android.support.v4.app.NotificationCompat;
//import android.support.v4.app.NotificationManagerCompat;




// komplett von: https://code.tutsplus.com/tutorials/how-to-recognize-user-activity-with-activity-recognition--cms-25851

public class ActivityRecognizedService extends IntentService {



    public ActivityRecognizedService() {
        super("ActivityRecognizedService");
    }

    public ActivityRecognizedService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if(ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities( result.getProbableActivities() );
        }
    }


    // ID angepasst müsste aber evtl noch hochgezählt werden....also das multiple activitys auch wenn gleich angezeigt werden...
    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {
        for( DetectedActivity activity : probableActivities ) {


            //long date = System.currentTimeMillis();

            //SimpleDateFormat sdf = new SimpleDateFormat("kk:mm:ss");
            //String time = sdf.format(date);
            String time = DataCollectionActivity.gettime ();


            switch( activity.getType() )

            {
                case DetectedActivity.IN_VEHICLE: {
                    Log.e( "ActivityRecogition", "In Vehicle: " + activity.getConfidence() );
                    if( activity.getConfidence() >= 50 ) {
                        //Hedwig.deliverNotification("Driving "+ activity.getConfidence()+"% at: "+time, 0, this, "Driving");
                        DatabaseInitializer.addToAsync (AppDatabase.getAppDatabase (this),"Driving", DataCollectionActivity.gettime (), activity.getConfidence(), 0,"% sure = value" );
                    }
                    break;
                }
                case DetectedActivity.ON_BICYCLE: {
                    Log.e( "ActivityRecogition", "On Bicycle: " + activity.getConfidence() );
                    if( activity.getConfidence() >= 50 ) {
                        //Hedwig.deliverNotification("On Bicycle "+ activity.getConfidence()+"% at: "+time, 1, this, "Bicycle");
                        DatabaseInitializer.addToAsync (AppDatabase.getAppDatabase (this),"Bicycle", DataCollectionActivity.gettime (), activity.getConfidence(), 0,"% sure = value" );
                    }
                    break;
                }
                case DetectedActivity.ON_FOOT: {
                    // debugging System.out.println("On Foot");
                    Log.e( "ActivityRecogition", "On Foot: " + activity.getConfidence() );
                    if( activity.getConfidence() >= 50 ) {
                        //Hedwig.deliverNotification("on Foot "+ activity.getConfidence()+"% at: "+time, 2, this, "Foot");
                        DatabaseInitializer.addToAsync (AppDatabase.getAppDatabase (this),"Foot", DataCollectionActivity.gettime (), activity.getConfidence(), 0,"% sure = value" );
                    }
                    break;
                }
                case DetectedActivity.RUNNING: {
                    Log.e( "ActivityRecogition", "Running: " + activity.getConfidence() );
                    if( activity.getConfidence() >= 50 ) {
                        //Hedwig.deliverNotification("Running "+ activity.getConfidence()+"% at: "+time, 3, this,"Running");
                        DatabaseInitializer.addToAsync (AppDatabase.getAppDatabase (this),"Running", DataCollectionActivity.gettime (), activity.getConfidence(), 0,"% sure = value" );
                    }
                    break;
                }
                case DetectedActivity.STILL: {
                    Log.e( "ActivityRecogition", "Still: " + activity.getConfidence() );
                    if( activity.getConfidence() >= 50 ) {
                        //Hedwig.deliverNotification("Standing still "+ activity.getConfidence()+"% at: "+time, 4, this,"Still");
                        DatabaseInitializer.addToAsync (AppDatabase.getAppDatabase (this),"Still", DataCollectionActivity.gettime (), activity.getConfidence(), 0,"% sure = value" );
                    }
                    break;
                }
                case DetectedActivity.TILTING: {
                    Log.e( "ActivityRecogition", "Tilting: " + activity.getConfidence() );
                    if( activity.getConfidence() >= 50 ) {
                        //Hedwig.deliverNotification("Tilting "+ activity.getConfidence()+"% at: "+time, 5, this,"Tilting");
                        DatabaseInitializer.addToAsync (AppDatabase.getAppDatabase (this),"Tilting", DataCollectionActivity.gettime (), activity.getConfidence(), 0,"% sure = value" );
                    }
                    break;
                }
                case DetectedActivity.WALKING: {
                    Log.e( "ActivityRecogition", "Walking: " + activity.getConfidence() );
                    if( activity.getConfidence() >= 50 ) {
                        //Hedwig.deliverNotification("Walking "+ activity.getConfidence()+"% at: "+time, 6, this, "Walking");
                        DatabaseInitializer.addToAsync (AppDatabase.getAppDatabase (this),"Walking", DataCollectionActivity.gettime (), activity.getConfidence(), 0,"% sure = value" );
                    }
                    break;
                }
                case DetectedActivity.UNKNOWN: {
                    Log.e( "ActivityRecogition", "Unknown: " + activity.getConfidence() );
                    if( activity.getConfidence() >= 50 ) {
                        //Hedwig.deliverNotification("WTF "+ activity.getConfidence()+"% at: "+time, 7, this,"WTF");
                        DatabaseInitializer.addToAsync (AppDatabase.getAppDatabase (this),"WTF", DataCollectionActivity.gettime (), activity.getConfidence(), 0,"% sure = value" );
                    }
                    break;
                }
            }
        }
    }


}