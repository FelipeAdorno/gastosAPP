var TipoMensagemEnum = {
  DANGER : {descricao: "DANGER", classe: "alert-danger"}, 
  SUCCESS: {descricao: "SUCCESS", classe: "alert-success"},
  INFO :   {descricao: "INFO", classe: "alert-info"},
  WARNING :{descricao: "WARNING", classe: "alert-warning"},
};

var MensagemRetorno = {
		
	    tratarMensagesRetorno : function(retorno) {
	    	if(retorno.mensagens.length > 0) {
	    		$(".alert").attr("class", "alert");
	    		if(TipoMensagemEnum.DANGER.descricao == retorno.tipoMensagemEnum){
	        		$(".alert").addClass(TipoMensagemEnum.DANGER.classe);
	        	}else if(TipoMensagemEnum.INFO.descricao == retorno.tipoMensagemEnum) {
	        		$(".alert").addClass(TipoMensagemEnum.INFO.classe);
	        	}else if(TipoMensagemEnum.SUCCESS.descricao == retorno.tipoMensagemEnum) {
	        		$(".alert").addClass(TipoMensagemEnum.SUCCESS.classe);
	        	}else if(TipoMensagemEnum.WARNING.descricao == retorno.tipoMensagemEnum) {
	        		$(".alert").addClass(TipoMensagemEnum.WARNING.classe);
	        	}
	        	var mensagesHtml = "";
	        	jQuery.each(retorno.mensagens, function( i, mensagem) {
	        		mensagesHtml += mensagem+"<br />";
	        	});
	        	$(".mensagens").html(mensagesHtml);
	    	}
	    	
	    },
	    tratarMensagemErroComunicacao : function() {
		    $(".alert").attr("class", "alert");
	    	$(".alert").addClass(TipoMensagemEnum.DANGER.classe);
	    	$(".mensagens").html("Erro na comunicação com o servidor, tente novamente.");
	    }
};