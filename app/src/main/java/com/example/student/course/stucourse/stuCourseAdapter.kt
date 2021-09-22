package com.example.student.course.stucourse

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.provider.Settings.System.getString
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.TypedArrayUtils.getString
import com.example.student.R
import com.example.student.course.stucourse.check.check_inActivity
import com.example.student.course.stucourse.rtmtoken.RtmTokenBuilder
import com.xuexiang.xui.utils.ResUtils
import io.agora.edu.launch.*


class stuCourseAdapter(private val context: Activity, private val newResourceId: Int, itemList: List<stuCourseListViewItem?>) : ArrayAdapter<stuCourseListViewItem?>(context, newResourceId, itemList){
    private var stuCourseListViewItem: stuCourseListViewItem? = null
    private lateinit var rtmToken: String
    private val tag = "stuCourseAdapter"
    private var mDialog: ForbiddenDialog? = null
    private val con=context
    val roomType = AgoraEduRoomType.AgoraEduRoomTypeBig.value
    val roleType = 2
    val userName = "123"
    val roomName = "123"
    val roomUuid = "123"
    val userUuid = userName.plus(roleType)
    var roomR = AgoraEduRegionStr.cn

    val startTime=System.currentTimeMillis()+100
    val duration=190L
    val appId = con.getString(R.string.agora_app_id)
    val appCertificate = con.getString(R.string.agora_app_cert)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        stuCourseListViewItem = getItem(position)
        val view = LayoutInflater.from(getContext()).inflate(newResourceId, parent, false)
        val coursename = view.findViewById<TextView>(R.id.stu_course_name)
        val teachername = view.findViewById<TextView>(R.id.stu_course_teachname)
        val courseimg = view.findViewById<ImageView>(R.id.stu_course_ImgV)
        stuCourseListViewItem!!.live = view.findViewById(R.id.live)
        stuCourseListViewItem!!.sign=view.findViewById(R.id.signin)

        AgoraEduSDK.setConfig(AgoraEduSDKConfig(appId!!,0))


        coursename.text = stuCourseListViewItem!!.getCoursename()
        teachername.text = stuCourseListViewItem!!.getTeachername()
        courseimg.setImageResource(stuCourseListViewItem!!.getCuorseImg())
        //实现直播按钮点击页面跳转效果

        //AgoraEduSDK.setConfig(AgoraEduSDKConfig(EduApplication.getAppId()!!, 0))
        stuCourseListViewItem!!.live.setOnClickListener(View.OnClickListener {
            //AgoraEduSDK.setConfig(AgoraEduSDKConfig(EduApplication.getAppId()!!, 0))
            rtmToken=RtmTokenBuilder().buildToken(appId, appCertificate, userUuid,
                    RtmTokenBuilder.Role.Rtm_User, 0)
            if (AppUtil.checkAndRequestAppPermission((con as Activity),arrayOf(Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE), AgoraEduSDK.REQUEST_CODE_RTC)) {
                val agoraEduLaunchConfig = AgoraEduLaunchConfig(userName, userUuid, roomName, roomUuid,
                        roleType, roomType, rtmToken, startTime, duration, roomR, null,
                        null)

                AgoraEduSDK.launch(con,agoraEduLaunchConfig){
                    state: AgoraEduEvent ->
                    Log.e(tag, ":launch-课堂状态:" + state.name)
                }
            } else {
            }
        }
                //else { }
        )


        stuCourseListViewItem!!.sign.setOnClickListener(View.OnClickListener {
            val intent=Intent(context,check_inActivity::class.java)
            context.startActivity(intent)
        })
        return view
    }



    public fun start() {

        //String rommName="123";



    }
    /*private fun notifyBtnJoinEnable(enable: Boolean) {
        (context as Activity).runOnUiThread { stuCourseListViewItem.live.isEnabled=enable }
    }*/



    private fun getRoomRegion(): String {
        return AgoraEduRegionStr.cn
        }
    private fun getRoomType(typeName: String): Int {
        return AgoraEduRoomType.AgoraEduRoomTypeBig.value

        }



}
