function () {
    var key = 1;

    var value = {
    	paga: this.paga,
    	valor:this.valor
	};
                
    emit(key, value);

};