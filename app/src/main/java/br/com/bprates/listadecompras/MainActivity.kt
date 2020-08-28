package br.com.bprates.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var itens: ArrayList<ItensCompras>? = ArrayList<ItensCompras>()
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        itens?.add(ItensCompras("Banana", false))
        itens?.add(ItensCompras("Maçã", false))
        itens?.add(ItensCompras("Água", false))

        adapter = ItemAdapter(itens!!, applicationContext)
        list.adapter = adapter
        list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val itensCompras: ItensCompras = itens!![position] as ItensCompras
            itensCompras.checked = !itensCompras.checked
            adapter.notifyDataSetChanged()
        }
        list.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, position, _ ->
            val itensCompras: ItensCompras = itens!![position] as ItensCompras
            itens!!.remove(itensCompras)
            this.adapter.notifyDataSetChanged()
            true
        }

        adiciona.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        if (id == R.id.adiciona && !novoItem.text.toString().equals("")) {
            itens?.add(ItensCompras(novoItem.text.toString(), false))
            setListaAdapter()
            novoItem.setText("")
        } else {
            Toast.makeText(this,"Por favor, insira um item no campo!",Toast.LENGTH_LONG).show()
        }
    }

    private fun setListaAdapter () {
        adapter.itensCompras = itens!!
        adapter.notifyDataSetChanged()
    }
}
