function fetchData() {
    let virusesRadioButton = $("#virusesRadioButton");
    let capitalsRadioButton = $("#capitalsRadioButton");
    let resultTitle = $("#resultTitle");

    if(virusesRadioButton.is(':checked') === true || capitalsRadioButton.is(':checked') === true) {
        virusesRadioButton.prop('checked',false);
        capitalsRadioButton.prop('checked',false);
        resultTitle.text("Your Choice");
    }

        virusesRadioButton.change(() => {

        if ($(virusesRadioButton).is(':checked')) {
            fetch('/viruses/show-table')
                .then(function(response) {
                    if(response.ok) {
                        return response.text();
                    } else {
                        throw Error();
                    }
                })
                .then(function(htmlContent) {
                    resultTitle.text("All Viruses");
                    $("#resultContainer").html(htmlContent);
                });
            };
        });

        capitalsRadioButton.change(() => {
            if ($(capitalsRadioButton).is(':checked')) {
            fetch('/capitals/show-table')
                .then(function(response) {
                    if(response.ok) {
                        return response.text();
                    } else {
                        throw Error();
                    }
                })
                .then(function(htmlContent) {
                    resultTitle.text("All Capitals");
                    $("#resultContainer").html(htmlContent);
                });
            };
        });
}