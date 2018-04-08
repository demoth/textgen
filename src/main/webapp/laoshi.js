
function onPageLoad () {
    document.getElementById("definition").value = "Enter chars";

    $.get( "/all_categories" + name, function (data, status) {
            console.log("Data: " + data + "\nStatus: " + status);
            var cat_list = document.getElementById("category_list");
            var cats_obj = JSON.parse(data)
            cat_list.innerHTML = createElementsForCategory(cats_obj);
        }
    );
}

function createElementsForCategory (cat_list_to_show) {
    result = "";
    console.log(cat_list_to_show)
    Object.keys(cat_list_to_show).forEach(function(cat) {
        result += "<li class='nav-item'> \
        <a class='nav-link' id='jobs_category' onclick='loadCategory(\"" + cat_list_to_show[cat] + "\"); return false;' href='#'>\
        <span data-feather='file'>\
        </span> " + cat_list_to_show[cat]  + "</a></li>";
    });
    return result;
}

function loadCategory (name) {
    $.get( "/category/" + name, function (data, status) {
            document.getElementById("definition").value = data;
            document.getElementById("category_name").innerHTML = name;
            console.log("Data: " + data + "\nStatus: " + status);
        }
    );
}

function save () {
    var cat = document.getElementById("category_name").value;
    var definition = document.getElementById("definition").value;
    $.post( "/save_category/" + name, definition, function (data, status) {

                console.log("Data: " + data + "\nStatus: " + status);
            }
        );
}
