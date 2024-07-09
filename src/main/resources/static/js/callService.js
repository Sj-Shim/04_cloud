function makeAjaxCall(url, formId, successCallback, failCallback) {
    // Get form data
    debugger;
    var formData = $('#' + formId).serializeArray();

    // Convert form data to JSON
    var jsonData = {};
    $.each(formData, function(i, field) {
        jsonData[field.name] = field.value;
    });

	console.log(JSON.stringify(jsonData));

    // Make AJAX request
    $.ajax({
        url: url,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(jsonData),
        dataType: 'json',
        success: function(response) {
            successCallback(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {
			failCallback(textStatus, errorThrown);
            console.error("Ajax call failed: ", textStatus, errorThrown);
        }
    });
}


function makeAjaxLoginCall(url, formId, successCallback, failCallback) {
    // Get form data
    debugger;
    var formData = $('#' + formId).serializeArray();

    // Convert form data to JSON
    var jsonData = {};
    $.each(formData, function(i, field) {
        jsonData[field.name] = field.value;
    });
	console.log(JSON.stringify(jsonData));

    // Make AJAX request
    $.ajax({
        url: url,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(jsonData),
        dataType: 'json',
        success: function(response) {
            successCallback(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {
			failCallback(textStatus, errorThrown);
            console.error("Ajax call failed: ", textStatus, errorThrown);
        }
    });
}


function updateServiceCall(url, jsonData, successCallback, failCallback) {
    // Get form data
    // Make AJAX request
    $.ajax({
        url: url,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(jsonData),
        dataType: 'json',
        success: function(response) {
            successCallback(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {
			failCallback(textStatus, errorThrown);
            console.error("Ajax call failed: ", textStatus, errorThrown);
        }
    });
}