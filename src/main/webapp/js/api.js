//---------------------------------------
// ex02
CalcApi = function(){};
CalcApi.CALC_ADD_URL = "/sample-jersey/api/calc/add";
CalcApi.CALC_SUB_URL = "/sample-jersey/api/calc/sub";
CalcApi.CALC_MUL_URL = "/sample-jersey/api/calc/mul";
CalcApi.CALC_DIV_URL = "/sample-jersey/api/calc/div";
CalcApi.prototype = {
	add: function(a, b, postFunc) {
		var url = CalcApi.CALC_ADD_URL;
		var data = new Object();
		data['a'] = a;
		data['b'] = b;

		$.ajax({
			type: "GET",
		    headers: {
		        'Accept': 'application/json',
		        'Content-Type': 'application/json; charset=utf-8'
		    },
			url: url,
			data: data,
			cache: true,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data) {
				postFunc(data);
			},
			error: function(data) {
				msg = 'エラーが発生しました(' + data.status + ':' + data.statusText + ')';
				alert(msg);
			},
			complete: function(){
			}
		});
	},
	sub: function(a, b, postFunc) {
		var url = CalcApi.CALC_SUB_URL;
		var data = new Object();
		data['a'] = a;
		data['b'] = b;

		$.ajax({
			type: "GET",
		    headers: {
		        'Accept': 'application/json',
		        'Content-Type': 'application/json; charset=utf-8'
		    },
			url: url,
			data: data,
			cache: true,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data) {
				postFunc(data);
			},
			error: function(data) {
				msg = 'エラーが発生しました(' + data.status + ':' + data.statusText + ')';
				alert(msg);
			},
			complete: function(){
			}
		});

	},
	mul: function(a, b, postFunc) {
		var url = CalcApi.CALC_MUL_URL;
		var data = new Object();
		data['a'] = a;
		data['b'] = b;

		$.ajax({
			type: "POST",
		    headers: {
		        'Accept': 'application/json',
		        'Content-Type': 'application/json; charset=utf-8'
		    },
			url: url,
			data: JSON.stringify(data),
			cache: true,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data) {
				postFunc(data);
			},
			error: function(data) {
				msg = 'エラーが発生しました(' + data.status + ':' + data.statusText + ')';
				alert(msg);
			},
			complete: function(){
			}
		});
	},
	div: function(a, b, postFunc) {
		var url = CalcApi.CALC_DIV_URL;
		var data = new Object();
		data['a'] = a;
		data['b'] = b;

		$.ajax({
			type: "GET",
		    headers: {
		        'Accept': 'application/json',
		        'Content-Type': 'application/json; charset=utf-8'
		    },
			url: url,
			data: data,
			cache: true,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data) {
				postFunc(data);
			},
			error: function(data) {
				msg = 'エラーが発生しました(' + data.status + ':' + data.statusText + ':' + data.responseText + ')';
				alert(msg);
			},
			complete: function(){
			}
		});
	}
};
var calcApi = new CalcApi();

//---------------------------------------
// ex03
UserApi = function(){};
UserApi.USER_URL = "/sample-jersey/api/user/";
UserApi.prototype = {
	load: function(id, postFunc) {
		var url = UserApi.USER_URL + id;

		$.ajax({
			type: "GET",
		    headers: {
		        'Accept': 'application/json',
		        'Content-Type': 'application/json; charset=utf-8'
		    },
			url: url,
			//data: data,
			cache: true,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data) {
				postFunc(id, data);
			},
			error: function(data) {
				msg = 'エラーが発生しました(' + data.status + ':' + data.statusText + ')';
				alert(msg);
			},
			complete: function(){
			}
		});
	},
	save: function(id, name, diskUsage, email, postFunc) {
		var url = UserApi.USER_URL + id;
		var data = new Object();
		data['id'] = id;
		data['name'] = name;
		data['diskUsage'] = diskUsage;
		data['email'] = email;

		$.ajax({
			type: "POST",
		    headers: {
		        'Accept': 'application/json',
		        'Content-Type': 'application/json; charset=utf-8'
		    },
			url: url,
			data: JSON.stringify(data),
			cache: true,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function(data) {
				postFunc(data);
			},
			error: function(data) {
				if (data.status == 400) {
					var msgObj =  JSON.parse(data.responseText);
					var msg = '';
					for (var i in msgObj) {
						msg += msgObj[i].message + '\n';
					}
					alert(msg);
				} else {
					var msg = 'エラーが発生しました(' + data.status + ':' + data.statusText + ')';
					alert(msg);
				}
			},
			complete: function(){
			}
		});
	}
};
var userApi = new UserApi();

//---------------------------------------
//ex04
ImageApi = function(){};
ImageApi.IMAGE_URL = "/sample-jersey/api/image/";
ImageApi.prototype = {
	upload: function(formData, postFunc) {
		var url = ImageApi.IMAGE_URL;
		$.ajax({
			type: "POST",
		    headers: {
		        'Accept': 'application/json',
		    },
			url: url,
			data: formData,
			processData: false,
            contentType: false,
			cache: false,
			dataType: "json",
			success: function(data) {
				postFunc(data);
			},
			error: function(data) {
				msg = 'エラーが発生しました(' + data.status + ':' + data.statusText + ')';
				alert(msg);
			},
			complete: function(){
			}
		});
	}
}
var imageApi = new ImageApi();
