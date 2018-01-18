export default {
  // Valida os componentes de uma página. Chama a validação de cada componente e exibe a mensagem,
  // caso os valores preenchidos são inválidos
  // #refs: referência para os componentes da página
  // #toast: referência para o componente de mensagem Toast
  validarComponentes (refs, refToast) {
    var invalido = 0
    // Validar os componentes referenciados
    Object.keys(refs).forEach(c => {
      if (typeof refs[c].validar === 'function' && !refs[c].validar()) {
        // Componente inválido
        invalido++
      }
    })
    if (invalido === 1) {
      // Apenas um componente inválido
      refToast.aviso('Favor corrigir o campo inválido')
    }
    else if (invalido > 1) {
      // Múltiplos componentes inválidos
      refToast.aviso('Favor corrigir os campos inválidos')
    }
  }
}
