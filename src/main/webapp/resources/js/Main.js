angular.module('AppGastos', [])
    .config(function ($httpProvider) {
        $httpProvider.responseInterceptors.push('HttpInterceptor');
        var spinnerFunction = function (data, headersGetter) {
            // todo start the spinner here
        	$.blockUI({ message: '<img src="../resources/images/loading.gif" />', centerY: 0, 
                css: { top: '10px', left: '', right: '10px' } });
            return data;
        };
        $httpProvider.defaults.transformRequest.push(spinnerFunction);
    })
// register the interceptor as a service, intercepts ALL angular ajax http calls
    .factory('HttpInterceptor', function ($q, $window) {
        return function (promise) {
            return promise.then(function (response) {
                // do something on success
                // todo hide the spinner
            	 $.unblockUI();
                return response;

            }, function (response) {
                // do something on error
                // todo hide the spinner
            	$.unblockUI();
                return $q.reject(response);
            });
        };
    });

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
			$('.monetario').maskMoney();
			$(".monetario").on('blur', function(ev){
				var valor = parseFloat($(".monetario").val().replace(".","").replace(",",".")).toFixed(2);
				angular.element($(this)).controller("ngModel").$setViewValue(valor);
			});
			$('#date').datepicker().on('changeDate', function(ev){
				if (window.angular && angular.element){
		            // Update the angular model
		            angular.element($(this).children()[0]).controller("ngModel").$setViewValue(ev.date);
				}
		    });
			$(".spinner").spinner('changing', function(e, newVal, oldVal){
				if (window.angular && angular.element){
		            // Update the angular model
		            angular.element($(this)).controller("ngModel").$setViewValue(newVal);
				}
			});
			
			$(".close").on('click', function(){
				$(".alert").addClass("nao-visivel");
			});
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