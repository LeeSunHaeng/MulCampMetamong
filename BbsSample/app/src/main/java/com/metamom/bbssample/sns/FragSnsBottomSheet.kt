package com.metamom.bbssample.sns

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.metamom.bbssample.R
import com.metamom.bbssample.subsingleton.SnsSingleton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragSnsBottomSheet.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragSnsBottomSheet(position:Int, adapter: FragSnsCustomAdapter, seq:Int, context: Context, imageContent:String) : BottomSheetDialogFragment() {
    val contxt = context
    val ad:FragSnsCustomAdapter = adapter
    val pos = position
    val sequence = seq
    val uri = imageContent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_frag_sns_bottom_sheet, container, false)
        val btnUpdate: Button = view.findViewById(R.id.FragbtUpdate)
        val btnDel: Button = view.findViewById(R.id.FragbtDelete)

        btnUpdate.setOnClickListener {
            SnsSingleton.position = pos
            SnsSingleton.code = "SNSUPDATE"
            val i = Intent(contxt,SnsUpdateActivity::class.java)
                i.putExtra("ImageContentUri",uri)
                i.putExtra("posi",pos)
                i.putExtra("seq",sequence)
            contxt.startActivity(i)
            dismiss()
        }

        btnDel.setOnClickListener {
            ad.delete(sequence)
            dismiss()
            Toast.makeText(context, "???????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show()
        }


        return view
    }
    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val contentView = View.inflate(context, R.layout.fragment_frag_sns_bottom_sheet, null)
        dialog?.setContentView(contentView)
    }


}