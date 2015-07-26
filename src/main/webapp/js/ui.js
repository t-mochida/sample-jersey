//---------------------------------------
// ex02
var initAddBtn = function() {
	$('#add-btn').click(function(){
		var a = $('#add-a').val();
		var b = $('#add-b').val();
		calcApi.add(a, b, postAdd);
	});
}
var postAdd = function(data) {
	var result = data.a + ' + ' + data.b + ' = ' + data.result;
	$('#add-result').text(result);
}

var initSubBtn = function() {
	$('#sub-btn').click(function(){
		var a = $('#sub-a').val();
		var b = $('#sub-b').val();
		calcApi.sub(a, b, postSub);
	});
}
var postSub = function(data) {
	var result = data.a + ' - ' + data.b + ' = ' + data.result;
	$('#sub-result').text(result);
}

var initMulBtn = function() {
	$('#mul-btn').click(function(){
		var a = $('#sub-a').val();
		var b = $('#sub-b').val();
		calcApi.mul(a, b, postMul);
	});
}
var postMul = function(data) {
	var result = data.a + ' * ' + data.b + ' = ' + data.result;
	$('#mul-result').text(result);
}

var initDivBtn = function() {
	$('#div-btn').click(function(){
		var a = $('#div-a').val();
		var b = $('#div-b').val();
		calcApi.div(a, b, postDiv);
	});
}
var postDiv = function(data) {
	var result = data.a + ' / ' + data.b + ' = ' + data.result;
	$('#div-result').text(result);
}

//---------------------------------------
//  ex03
var initUserSaveBtn = function() {
	$('#user-save-btn').click(function(){
		var id = $('#user-save-id').val();
		var name = $('#user-name').val();
		var diskUsage = $('#user-disk-usage').val();
		var email = $('#user-email').val();
		userApi.save(id, name, diskUsage, email, postUserSave);
	});
}
var postUserSave = function(result) {
	alert(result.msg);
}

var initUserLoadBtn = function() {
	$('#user-load-btn').click(function(){
		var id = $('#user-load-id').val();
		userApi.load(id, postUserLoad);
	});
}
var postUserLoad = function(id, result) {
	var data = result.data;
	var user = 'ID:' + id + ', ユーザ名:' + data.name + ', ディスク使用率:' + data.diskUsage + ', メールアドレス:' + data.email;
	$('#user-data').text(user);
}


//---------------------------------------
//ex04
var initImageUploader = function() {
	$('#img-file').change(function() {
		if (!this.files.length) {
			return;
		}
		var file = this.files[0];
        var fileReader = new FileReader();
		fileReader.onload = function(e) {
			$('#img').attr('src', e.target.result);
			$('#img-type').val(file.type);
			$('#img-size').val(file.size);
		}
        fileReader.readAsDataURL(file);
    });

	$('#img-upload-btn').click(function(){
		var $form = $('#img-form');
		var formData = new FormData( $form[0] );
		imageApi.upload(formData, postImageUpload);
	});
}
var postImageUpload = function(result) {
	console.log(result.path);
	alert(result.path);
}
