package com.example.sistemadecompras

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import java.util.Locale

/**
 * Tela principal do Sistema de Compras.
 *
 * Fluxo do aplicativo:
 *  1. O usuário marca os produtos desejados (Arroz, Leite, Carne, Feijão).
 *  2. Ao clicar em "Total das compras", é exibido o resumo com o valor total.
 *  3. Ao clicar em "OK", é exibida a seleção da forma de pagamento
 *     (PIX, Cartão de Crédito, Cartão de Débito ou Dinheiro).
 *  4. Após escolher a forma de pagamento, é exibida a mensagem de
 *     "Pagamento realizado com sucesso".
 */
class MainActivity : Activity() {

    // Preços de cada produto.
    private val precoArroz = 2.69
    private val precoLeite = 5.00
    private val precoCarne = 9.70
    private val precoFeijao = 2.70

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cbArroz = findViewById<CheckBox>(R.id.cb_arroz)
        val cbLeite = findViewById<CheckBox>(R.id.cb_leite)
        val cbCarne = findViewById<CheckBox>(R.id.cb_carne)
        val cbFeijao = findViewById<CheckBox>(R.id.cb_feijao)
        val btTotal = findViewById<Button>(R.id.bt_total_compras)

        // Botão "Total das compras" -> calcula o total e mostra o resumo.
        btTotal.setOnClickListener {
            var total = 0.0
            if (cbArroz.isChecked) total += precoArroz
            if (cbLeite.isChecked) total += precoLeite
            if (cbCarne.isChecked) total += precoCarne
            if (cbFeijao.isChecked) total += precoFeijao

            mostrarResumoDaCompra(total)
        }
    }

    /**
     * Exibe o resumo da compra com o valor total.
     * Ao clicar em "OK" avança para a escolha da forma de pagamento.
     */
    private fun mostrarResumoDaCompra(total: Double) {
        val valorFormatado = String.format(Locale.US, "R$ %.2f", total)

        AlertDialog.Builder(this)
            .setTitle("Resumo da Compra")
            .setMessage("Valor total da compra : $valorFormatado")
            .setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
            .setPositiveButton("OK") { _, _ -> mostrarFormaDePagamento() }
            .show()
    }

    /**
     * Exibe a lista de formas de pagamento disponíveis.
     * Ao selecionar uma opção, mostra a mensagem de pagamento realizado.
     */
    private fun mostrarFormaDePagamento() {
        val formasPagamento = arrayOf(
            "PIX",
            "Cartão de Crédito",
            "Cartão de Débito",
            "Dinheiro"
        )

        AlertDialog.Builder(this)
            .setTitle("Selecione a Forma de Pagamento")
            .setItems(formasPagamento) { _, which ->
                mostrarPagamentoRealizado(formasPagamento[which])
            }
            .show()
    }

    /**
     * Exibe a mensagem final confirmando o pagamento realizado com sucesso.
     */
    private fun mostrarPagamentoRealizado(formaPagamento: String) {
        AlertDialog.Builder(this)
            .setTitle("Pagamento")
            .setMessage("Pagamento realizado com sucesso via $formaPagamento!")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
