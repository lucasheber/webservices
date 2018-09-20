let appid = '78dcc54d045b28279bb035fa5290c106'
let wsurl_city = 'https://api.openweathermap.org/data/2.5/weather'
let url_icon = 'http://openweathermap.org/img/w/'
let api_key = 'AIzaSyCetxLR_Ed0D_ZjpKhy5igRj5NHwHlZA0E'

$(function () {
    $(".weather").hide();

    $('.city').keyup(function(e){
        if(e.keyCode == 13) {
            wsOpenWeather();
        }
    });

    $('.onSearch').click(function () {
        wsOpenWeather();
    });
});

function wsOpenWeather() {
    var city = $('.city').val();
    var lat, lon

    $.ajax({
        url: wsurl_city,
        type: 'GET',
        data: {'q': city, 'APPID': appid},
        dataType: 'json',

        // beforeSend: function( xhr ) {
        //     console.log(xhr)
        // }

        success: function (response) {
            console.log(response)

            lat = response.coord.lat
            lon = response.coord.lon

            $("#city").html(response.name)
            $("#celcius").html(fahrenheitToCelcius(response.main.temp) + "°")
            $("#temp-max").html(fahrenheitToCelcius(response.main.temp_max))
            $("#temp-min").html(fahrenheitToCelcius(response.main.temp_min))
            $("#pressure").html(pressure(response.main.pressure))

            $("#lat").html(lat)
            $("#lon").html(lon)

            initMap(lat, lon);

            $("#sunrise").html(timestampToDate(response.sys.sunrise))
            $("#sunset").html(timestampToDate(response.sys.sunset))

            $("#img_icon").attr("src", url_icon + response.weather[0].icon + '.png')

            $(".weather").show();
        },

        statusCode: {
            404: function(){
                showMessage('Cidade não encontrada', 'error')
            }
        }
    })

}

function fahrenheitToCelcius(graus) {
    return parseInt(graus - 273.15);
}

function  showMessage(message, error) {
    if(error == 'error') alert(message)
}

function timestampToDate(timestamp){
    return 0;
}

function pressure(pressure) {
    return pressure
}

function initMap(latitude, longitude) {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat:latitude, lng: longitude},
        zoom: 8
    });
}