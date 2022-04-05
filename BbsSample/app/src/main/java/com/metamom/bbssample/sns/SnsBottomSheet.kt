package com.metamom.bbssample.sns

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context

import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.metamom.bbssample.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SnsBottomSheet.newInstance] factory method to
 * create an instance of this fragment.
 */
class SnsBottomSheet(position:Int,adapter: CustomAdapter,seq:Int,context:Context) : BottomSheetDialogFragment(){

    val ad:CustomAdapter = adapter
    val pos = position
    val sequence = seq

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sns_bottom_sheet, container, false)
        val btnDel:Button = view.findViewById(R.id.btDelete)

        btnDel.setOnClickListener {
            Toast.makeText(context, "게시물이 삭제 되었습니다.", Toast.LENGTH_SHORT).show()
            ad.delete(pos,sequence)
            dismiss()
        }


        // Inflate the layout for this fragment
        return view
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val contentView = View.inflate(context, R.layout.fragment_sns_bottom_sheet, null)
        dialog?.setContentView(contentView)
    }


/*    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btUpdate ->{

            }
            R.id.btDelete ->{
                Log.i("Button", "delete click")
               ad.delete(pos,sequence)
            }
        }
    }*/
}