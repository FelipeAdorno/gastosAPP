var menuEnum = {
  HOME : 		{id: "menuHome"}, 
  CADASTRO: 	{id: "menuCadastro"},
  RELATORIO :   {id: "menuRelatorio"},
};

$(document).ready(function(){
	Main.init();
});

var Main = {
		init : function() {
			$('.tel').mask('(99)9999-9999');
			$('.cnpj').mask('99.999.999/9999-99');
			$('#date').datepicker(new Date());
		},
		ativarMenu : function(menu) {
			Main.limparMenusAtivo();
			if(menu == menuEnum.HOME.id){
				 $("#"+menuEnum.HOME.id).addClass("active");
			}else if(menu == menuEnum.CADASTRO.id){
				 $("#"+menuEnum.CADASTRO.id).addClass("active");
			}else if(menu == menuEnum.RELATORIO.id){
				 $("#"+menuEnum.RELATORIO.id).addClass("active");
			}
		},
		limparMenusAtivo : function() {
			for(var menu in menuEnum) {
			    $("#"+menu.id).removeClass("active");
			}
		}
};