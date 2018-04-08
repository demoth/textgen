


function loadCategory (name) {
    $.ajax({
      type: 'GET',
      url: "/category/" + name,
      success: function (data, status) {
        document.getElementById("definition").value = data
        document.getElementById("category_name").innerHTML  = name
        console.log("Data: " + data + "\nStatus: " + status);}
    });

}