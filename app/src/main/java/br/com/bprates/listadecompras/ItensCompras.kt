package br.com.bprates.listadecompras

class ItensCompras {
    var nome: String = ""
    var checked: Boolean = false

    constructor() {}

    constructor(nome: String, checked: Boolean) {
        this.nome = nome
        this.checked = checked
    }
}