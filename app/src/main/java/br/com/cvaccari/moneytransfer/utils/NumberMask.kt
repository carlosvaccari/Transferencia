package br.com.cvaccari.moneytransfer.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

object NumberMask {

    val FORMAT_FONE_11 = "(##) #####-####"

    /**
     * Método que deve ser chamado para realizar a formatação
     *
     * @param ediTxt
     * @param mask
     * @return
     */
    fun mask(ediTxt: EditText, mask: String): TextWatcher {
        return object : TextWatcher {
            internal var isUpdating: Boolean = false
            internal var old = ""

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = NumberMask.unmask(s.toString())
                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }
                var i = 0
                for (m in mask.toCharArray()) {
                    if (m != '#' && str.length > old.length) {
                        mascara += m
                        continue
                    }
                    try {
                        mascara += str[i]
                    } catch (e: Exception) {
                        break
                    }

                    i++
                }
                isUpdating = true
                ediTxt.setText(mascara)
                ediTxt.setSelection(mascara.length)
            }
        }
    }

    fun addMask(textoAFormatar: String): String {
        val unmasked = unmaskPhone(textoAFormatar)
        val mask = FORMAT_FONE_11

        var formatado = ""
        var i = 0
        // vamos iterar a mascara, para descobrir quais caracteres vamos adicionar e quando...
        for (m in mask.toCharArray()) {
            if (m != '#') { // se não for um #, vamos colocar o caracter informado na máscara
                formatado += m
                continue
            }
            // Senão colocamos o valor que será formatado
            try {
                formatado += unmasked[i]
            } catch (e: Exception) {
                break
            }

            i++
        }
        return formatado
    }

    fun unmask(s: String): String {
        return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
            .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "").replace("[ ]".toRegex(), "")
            .replace("[:]".toRegex(), "").replace("[)]".toRegex(), "").replace("[+]".toRegex(), "")
            .replace("[,]".toRegex(), "")
    }

    fun unmaskPhone(s: String): String {
        return s.replace("[.]".toRegex(), "")
            .replace("[-]".toRegex(), "")
            .replace("[/]".toRegex(), "")
            .replace("[(]".toRegex(), "")
            .replace("[ ]".toRegex(), "")
            .replace("[:]".toRegex(), "")
            .replace("[)]".toRegex(), "")
            .replace("[+]".toRegex(), "")
            .replace("[,]".toRegex(), "")
    }
}