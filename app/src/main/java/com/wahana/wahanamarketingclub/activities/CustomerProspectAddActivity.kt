package com.wahana.wahanamarketingclub.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.gson.Gson
import com.wahana.wahanamarketingclub.R
import com.wahana.wahanamarketingclub.activities.LoginActivity.Companion.MY_LOGIN_PREF
import com.wahana.wahanamarketingclub.activities.LoginActivity.Companion.MY_LOGIN_PREF_KEY
import com.wahana.wahanamarketingclub.connect.API
import com.wahana.wahanamarketingclub.fragments.MasterPosCodeFragment
import com.wahana.wahanamarketingclub.model.*
import kotlinx.android.synthetic.main.activity_customer_prospect_add.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList
import android.app.DatePickerDialog
import com.wahana.wahanamarketingclub.fragments.MasterLovCustomerProspectFragment
import java.text.SimpleDateFormat
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.AdapterView
import android.widget.EditText
import com.wahana.wahanamarketingclub.utils.FileUtils
import kotlin.collections.HashMap
import id.zelory.compressor.*
import java.io.File
import java.text.ParseException

/**
 * Created by Lely
 */

class CustomerProspectAddActivity : AppCompatActivity() {

    var datareligion: List<MasterReligion>? = null
    var dataJenisCustomer: List<MasterJenisCustomer>? = null
    var dataTypeCustomer: List<MasterTypeCustomer>? = null
    var dataOccupation: List<MasterOccupation>? = null
    var fileUploads = ArrayList<FileUpload>()
    var uploadSelectedIndex = 0
    lateinit var editTextToUploadSelectedIndex: HashMap<Int, EditText>

    lateinit var id: String
    lateinit var code: String

    var pos_id : String? = ""
    val myCalendar = Calendar.getInstance()

    private val GALLERY_INTENT_CALLED: Int = 1

    private val GALLERY_KITKAT_INTENT_CALLED: Int = 2

    private val MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE: Int = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_prospect_add)


        //Set Toolbar
        titleSearch.setText("Customer Prospect")
        titleSearch.setTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        spnJenisCustomer.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                val jenis = spnJenisCustomer.selectedItem.toString()
                if((jenis.equals("Perusahaan"))){
                    tx_cust_pic.visibility = View.VISIBLE
                    cust_pic.visibility = View.VISIBLE
                }else{
                    tx_cust_pic.visibility = View.GONE
                    cust_pic.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
            }
        }

        getReligion()
        getMasterJenisCustomer()
        getMasterTypeCustomer()
        getOccupation()
        getJenisKelamin()

        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }

        cust_tanggal_lahir.setOnClickListener {
                // TODO Auto-generated method stub
                DatePickerDialog(this@CustomerProspectAddActivity, R.style.DatePickerDialogTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }

        btnCustomerProspect.setOnClickListener{
            btnCustomerProspect.visibility = View.INVISIBLE
            progressBar.visibility=View.VISIBLE
            inserCustomerProspect()
        }

        editTextToUploadSelectedIndex = hashMapOf(
                0 to doc_1, 1 to doc_2, 2 to doc_3, 3 to doc_4, 4 to doc_5
        )


        doc_1.setOnClickListener {
            uploadSelectedIndex = 0
            if(checkCountImage()){
                if (checkPermission()) {
                    getFileChooserIntent()
                }
            }
        }

        doc_2.setOnClickListener {
            uploadSelectedIndex = 1
            if(checkCountImage()){
                if (checkPermission()) {
                    getFileChooserIntent()
                }
            }
        }

        doc_3.setOnClickListener {
            uploadSelectedIndex = 2
            if(checkCountImage()){
                if (checkPermission()) {
                    getFileChooserIntent()
                }
            }
        }

        doc_4.setOnClickListener {
            uploadSelectedIndex = 3
            if(checkCountImage()){
                if (checkPermission()) {
                    getFileChooserIntent()
                }
            }
        }

        doc_5.setOnClickListener {
            uploadSelectedIndex = 4
            if(checkCountImage()){
                if (checkPermission()) {
                    getFileChooserIntent()
                }
            }
        }
    }

    private fun checkCountImage() : Boolean{
        if(fileUploads.size >= uploadSelectedIndex){
            return true;
        }else{
            Toast.makeText(this@CustomerProspectAddActivity, "Isi dokumen yang belum diisi", Toast.LENGTH_LONG).show()
            return false;
        }
    }

    private fun getFileChooserIntent() {

        if (Build.VERSION.SDK_INT < 19) {
            val intent = Intent()
            intent.type = "*/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_INTENT_CALLED)
        } else {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "*/*"
            startActivityForResult(intent, GALLERY_KITKAT_INTENT_CALLED)
        }
    }

    @SuppressLint("NewApi")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) return;

        data?.let {
            val originalUri: Uri = it.data;
            if (requestCode == GALLERY_KITKAT_INTENT_CALLED) {
                val takeFlags = data.flags and (Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                // Check for the freshest data.
                contentResolver.takePersistableUriPermission(originalUri, takeFlags)
            }

            val type = contentResolver.getType(originalUri)
            val filePath = FileUtils.getPath(this, originalUri)
            val extension = FileUtils.getExtension(filePath)
            val fileName = FileUtils.getFileName(filePath)
            val receivableExtension = arrayOf(".png", ".jpg", "jpeg")
            if (extension != null && filePath != null && receivableExtension.contains(extension.toLowerCase())) {
                try {
                    val bitmap = Compressor(this)
                            .setMaxWidth(500)
                            .setMaxHeight(340)
                            .setQuality(75)
                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                            .compressToBitmap(File(filePath));

                    fileUploads.add(uploadSelectedIndex, FileUpload(bitmap, fileName, type))
                    editTextToUploadSelectedIndex[uploadSelectedIndex]?.setText(fileName)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }else{
                Toast.makeText(this@CustomerProspectAddActivity, "Image Only", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun checkPermission() : Boolean {
       if(Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.M) {
           if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
               if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                   val alertBuilder = AlertDialog.Builder(this);
                   alertBuilder.setCancelable(true);
                   alertBuilder.setTitle("Permission necessary");
                   alertBuilder.setMessage("Read access to storage is needed!");
                   alertBuilder.setPositiveButton(android.R.string.yes, DialogInterface.OnClickListener { dialog, which ->
                       ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
                   })
                   alertBuilder.create().show();
               } else {
                   ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
               }
               return false;
           } else {
               return true;
           }
       } else {
           return true;
       }
   }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getFileChooserIntent()
            }
        }
    }

    inner class FileUpload(var bitmap: Bitmap, var fileName: String, var type: String)

    private fun updateLabel() {
        val myFormat = "yyyy-MM-dd" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        cust_tanggal_lahir.setText(sdf.format(myCalendar.time))
    }

    fun isValid() : Boolean {
        var valid : Boolean = true
        val nama = cust_nama.getText().toString()
        val alamat = cust_alamat.getText().toString()
        val hp = cust_hp.getText().toString()
        val email = cust_email.getText().toString()

        //validasi nama
        if (nama.isEmpty()) {
            cust_nama.setError("Nama belum diisi")
            valid = false
        } else if (nama.length < 3) {
            cust_nama.setError("Nama minimal 3 karakter")
            valid = false
        } else {
            cust_nama.setError(null)
        }

        //validasi alamat
        if (alamat.isEmpty()) {
            cust_alamat.setError("Alamat belum diisi")
            valid = false
        }
        else {
            cust_alamat.setError(null)
        }

        //validasi hp
        if (hp.isEmpty()) {
            cust_hp.setError("Hp belum diisi")
            valid = false
        }
        else {
            cust_hp.setError(null)
        }

        //validasi email
        if (!isValidEmail(email)) {
            cust_email.setError("Email salah format")
            valid = false
        } else {
            cust_email.setError(null)
        }

        return valid
    }

    private fun isValidEmail(email : String): Boolean {
        val EMAIL_PATTERN = "(^\$|^.*@.*\\..*\$)"
        val pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher = pattern.matcher(cust_email.text.toString())
        return matcher.matches()
    }

    fun inserCustomerProspect() {
        if (!isValid()) {
            btnCustomerProspect.visibility = View.VISIBLE
            progressBar.visibility=View.INVISIBLE
            return
        }

        val savedUser = Gson().fromJson<LoginUser>(this@CustomerProspectAddActivity.getSharedPreferences(MY_LOGIN_PREF, Context.MODE_PRIVATE).getString(MY_LOGIN_PREF_KEY, ""), LoginUser::class.java)
        val branch_id = savedUser.branch_id
        val salesman_id = savedUser.salesmanId
        val id_user = savedUser.id

        val dataCustomerProspect = CustomerProspectAdd()

        dataCustomerProspect.custCode = "CODE"
        dataCustomerProspect.custNama = cust_nama.text.toString()
        dataCustomerProspect.custHp = cust_hp.text.toString()
        dataCustomerProspect.custHpWa = cust_hp_wa.text.toString()
        dataCustomerProspect.custAlamat = cust_alamat.text.toString()
        dataCustomerProspect.custRt = cust_rt.text.toString()
        dataCustomerProspect.custRw = cust_rw.text.toString()

        dataCustomerProspect.poscodeId = pos_id
        dataCustomerProspect.posKode = kodePosEditText.text.toString()
        dataCustomerProspect.posLurah = kelurahanEditText.text.toString()
        dataCustomerProspect.posCamat = kecamatanEditText.text.toString()
        dataCustomerProspect.posKota = kotaEditText.text.toString()
        dataCustomerProspect.posProp = provinsiEditText.text.toString()
        dataCustomerProspect.kotaId = idKotaEditText.text.toString()
        dataCustomerProspect.propinsiId = idProvinsiEditText.text.toString()

        dataCustomerProspect.custEmail = cust_email.text.toString()
        if(spnJenisKelamin.selectedItem == "Perempuan"){
            dataCustomerProspect.custKelamin = "P"
        }else {
            dataCustomerProspect.custKelamin = "L"
        }
        dataCustomerProspect.custTglLahir = cust_tanggal_lahir.text.toString()
        dataCustomerProspect.custAgama = (spnReligion.selectedItem as? MasterReligion)?.code
        dataCustomerProspect.kerjaKode = (spnOccupation.selectedItem as? MasterOccupation)?.code
        dataCustomerProspect.custNpwp = cust_npwp.text.toString()
        dataCustomerProspect.custJenis = (spnJenisCustomer.selectedItem as? MasterJenisCustomer)?.code
        dataCustomerProspect.custType = (spnTypeCustomer.selectedItem as? MasterTypeCustomer)?.code
        dataCustomerProspect.custPic = cust_pic.text.toString()
        dataCustomerProspect.custNoktp = cust_noktp.text.toString()
        dataCustomerProspect.custTelpRumah = cust_telp_rumah.text.toString()
        dataCustomerProspect.custTelpKantor = cust_telp_kantor.text.toString()
        dataCustomerProspect.custFax = cust_fax.text.toString()
        dataCustomerProspect.branchId = branch_id
        dataCustomerProspect.salesmanId = salesman_id
        dataCustomerProspect.id = id_user
        dataCustomerProspect.occupationId = (spnOccupation.selectedItem as? MasterOccupation)?.id

        API.insertCustomerProspectMultipart(dataCustomerProspect, fileUploads).enqueue(object : Callback<CustomerProspectAdd> {
            override fun onResponse(call: Call<CustomerProspectAdd>, response: Response<CustomerProspectAdd>) {
                if (response.code() == 200) {
                    val user = response.body()
                    startActivity(Intent(this@CustomerProspectAddActivity, CustomerProspectIndexActivity::class.java))
                    finish()
                    Toast.makeText(this@CustomerProspectAddActivity, "Add Data Success", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@CustomerProspectAddActivity, "Add Data Failed", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<CustomerProspectAdd>, t: Throwable) {
                Toast.makeText(this@CustomerProspectAddActivity, "Error", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getReligion(){
        API.getreligion().enqueue(object : Callback<ArrayList<MasterReligion>> {

            override fun onResponse(call: Call<ArrayList<MasterReligion>>, response: Response<ArrayList<MasterReligion>>) {
                if (response.code() == 200) {
                    datareligion = response.body()
                    val adapter = ArrayAdapter<MasterReligion>(this@CustomerProspectAddActivity, R.layout.spinner_custom, datareligion)
                    adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
                    spnReligion.setAdapter(adapter)
                }
            }

            override fun onFailure(call: Call<ArrayList<MasterReligion>>, throwable: Throwable) {
                Toast.makeText(this@CustomerProspectAddActivity, "Error", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getMasterJenisCustomer(){

        API.getMasterJenisCustomer().enqueue(object : Callback<ArrayList<MasterJenisCustomer>> {

            override fun onResponse(call: Call<ArrayList<MasterJenisCustomer>>, response: Response<ArrayList<MasterJenisCustomer>>) {
                if (response.code() == 200) {
                    dataJenisCustomer = response.body()
                    val adapter = ArrayAdapter<MasterJenisCustomer>(this@CustomerProspectAddActivity, R.layout.spinner_custom, dataJenisCustomer)
                    adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
                    spnJenisCustomer.setAdapter(adapter)


                }
            }

            override fun onFailure(call: Call<ArrayList<MasterJenisCustomer>>, throwable: Throwable) {
                Toast.makeText(this@CustomerProspectAddActivity, "Error", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getMasterTypeCustomer(){
        API.getMasterTypeCustomer().enqueue(object : Callback<ArrayList<MasterTypeCustomer>> {

            override fun onResponse(call: Call<ArrayList<MasterTypeCustomer>>, response: Response<ArrayList<MasterTypeCustomer>>) {
                if (response.code() == 200) {
                    dataTypeCustomer = response.body()
                    val adapter = ArrayAdapter<MasterTypeCustomer>(this@CustomerProspectAddActivity, R.layout.spinner_custom, dataTypeCustomer)
                    adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
                    spnTypeCustomer.setAdapter(adapter)
                }
            }

            override fun onFailure(call: Call<ArrayList<MasterTypeCustomer>>, throwable: Throwable) {
                Toast.makeText(this@CustomerProspectAddActivity, "Error", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getOccupation(){
        API.getMasterOccupation().enqueue(object : Callback<ArrayList<MasterOccupation>> {

            override fun onResponse(call: Call<ArrayList<MasterOccupation>>, response: Response<ArrayList<MasterOccupation>>) {
                if (response.code() == 200) {
                    dataOccupation = response.body()
                    val adapter = ArrayAdapter<MasterOccupation>(this@CustomerProspectAddActivity, R.layout.spinner_custom, dataOccupation)
                    adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
                    spnOccupation.setAdapter(adapter)

                }
            }

            override fun onFailure(call: Call<ArrayList<MasterOccupation>>, throwable: Throwable) {
                Toast.makeText(this@CustomerProspectAddActivity, "Error", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getJenisKelamin(){

        val items = arrayOf("Perempuan", "Laki-Laki")

        val adapter = ArrayAdapter<String>(this@CustomerProspectAddActivity, R.layout.spinner_custom, items)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spnJenisKelamin.setAdapter(adapter)
    }

    fun getMasterKodePos(view :View){
        MasterPosCodeFragment.createInstance(onSelectMasterKodePos).show(supportFragmentManager, "")
    }

    fun getMasterLovCustomer(view :View){
        MasterLovCustomerProspectFragment.createInstance(onSelectMasterLovCustomers).show(supportFragmentManager, "")
    }

    val onSelectMasterKodePos = { posCode: MasterPosCode? ->
        kodePosEditText.setText(posCode?.posKode)
        kelurahanEditText.setText(posCode?.posLurah)
        kecamatanEditText.setText(posCode?.posCamat)
        kotaEditText.setText(posCode?.posKota)
        provinsiEditText.setText(posCode?.posProp)
        idKotaEditText.setText(posCode?.kotaId)
        idProvinsiEditText.setText(posCode?.propinsiId)
        pos_id = posCode?.id
    }

    val onSelectMasterLovCustomers = { customers: MasterLovCustomers? ->
        cust_nama.setText(customers?.custNama)
        cust_alamat.setText(customers?.custAlamat)
        cust_hp.setText(customers?.custHp)
        cust_rt.setText(customers?.custRt)
        cust_rw.setText(customers?.custRw)
        kodePosEditText.setText(customers?.posKode)
        kelurahanEditText.setText(customers?.posLurah)
        kecamatanEditText.setText(customers?.posCamat)
        kotaEditText.setText(customers?.posKota)
        provinsiEditText.setText(customers?.posProp)
        idKotaEditText.setText(customers?.kotaId)
        idProvinsiEditText.setText(customers?.propinsiId)
        cust_email.setText(customers?.custEmail)

        val date = customers?.custTglLahir

        if(date != null){
            val outputFormat = SimpleDateFormat("yyyy-MM-dd")
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            inputFormat.timeZone = TimeZone.getTimeZone("GMT")
            try {
                val dap = inputFormat.parse(date)
                val outputText = outputFormat.format(dap)
                cust_tanggal_lahir.setText(outputText)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        cust_npwp.setText(customers?.custNpwp)
        cust_pic.setText(customers?.custPic)
        cust_noktp.setText(customers?.custNoktp)
        cust_telp_rumah.setText(customers?.custTelpRumah)
        cust_telp_kantor.setText(customers?.custTelpKantor)
        cust_fax.setText(customers?.custFax)

        if(customers?.custKelamin.equals("L")){
            spnJenisKelamin.setSelection((spnJenisKelamin.getAdapter() as ArrayAdapter<String>).getPosition("Laki-Laki"))
        }else{
            spnJenisKelamin.setSelection((spnJenisKelamin.getAdapter() as ArrayAdapter<String>).getPosition("Perempuan"))
        }

        spnReligion.setSelection(datareligion?.indexOfFirst { it.code == customers?.custAgama } ?: 0);
        spnOccupation.setSelection(dataOccupation?.indexOfFirst { it.code == customers?.kerjaKode } ?: 0);
        spnJenisCustomer.setSelection(dataJenisCustomer?.indexOfFirst { it.code == customers?.custJenis } ?: 0);
        spnTypeCustomer.setSelection(dataTypeCustomer?.indexOfFirst { it.code == customers?.custType } ?: 0);
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
private operator fun String.invoke(toString: String) {}
