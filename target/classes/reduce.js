function(key, values) {
	
	var res = { total: 0, totalPagao: 0, restante: 0 };
    values.forEach(function(v) {
    	if (v.paga){
		    res.totalPagao= res.totalPagao + parseFloat(v.valor);
	    }else{
	    	res.restante = res.restante + parseFloat(v.valor);
	    }
    	
    	res.total = res.total + parseFloat(v.valor)
 
    });
    
    res.total.toFixed(2);
    res.restante.toFixed(2);
    res.totalPagao.toFixed(2);
    return res;
}