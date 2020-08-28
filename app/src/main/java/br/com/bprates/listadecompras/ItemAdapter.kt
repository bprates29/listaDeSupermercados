package br.com.bprates.listadecompras

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView

class ItemAdapter(private val itens: ArrayList<ItensCompras>, mContext: Context):
    ArrayAdapter<ItensCompras>(mContext,R.layout.listview_and_checkbox, itens) {
    var itensCompras: ArrayList<ItensCompras> = itens

    private class ViewSuporte {
        lateinit var txtView: TextView
        lateinit var checkBox: CheckBox
    }

    override fun getCount(): Int {
        return itensCompras.size
    }

    override fun getItem(posicao: Int): ItensCompras {
        return itensCompras[posicao]
    }

    override fun getView(posicao: Int, convertView: View?, parent: ViewGroup): View {
        var convertView2 = convertView
        val viewSuporte: ViewSuporte
        val resultado: View
        if (convertView == null) {
            viewSuporte = ViewSuporte()
            convertView2 = LayoutInflater.from(parent.context).inflate(
                R.layout.listview_and_checkbox, parent, false)
            viewSuporte.txtView = convertView2.findViewById(R.id.textView)
            viewSuporte.checkBox = convertView2.findViewById(R.id.checkBox)
            viewSuporte.checkBox.setOnCheckedChangeListener { _, isChecked ->
                //Toast.makeText(parent.context,isChecked.toString(),Toast.LENGTH_SHORT).show()
                if (isChecked) {
                    viewSuporte.txtView.setTypeface(viewSuporte.txtView.typeface,Typeface.ITALIC)
                    viewSuporte.txtView.paintFlags =
                        viewSuporte.txtView.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    viewSuporte.txtView.setTypeface(viewSuporte.txtView.typeface,Typeface.BOLD)
                    viewSuporte.txtView.paintFlags =
                        viewSuporte.txtView.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
            }
            resultado = convertView2
            convertView2.tag = viewSuporte
        } else {
            viewSuporte = convertView2!!.tag as ViewSuporte
            resultado = convertView2
        }
        val item: ItensCompras = getItem(posicao)
        viewSuporte.txtView.text = item.nome
        viewSuporte.checkBox.isChecked = item.checked
        return resultado
    }



}