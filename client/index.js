// Подключаем библиотеку Leaflet.
var L = window.L;

// Определяем начальные параметры карты.
var map = L.map('map').setView([51.505, -0.09], 13);

// Добавляем базовый слой OpenStreetMap.
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

// Функция для загрузки изображения с сервера и его отображения на карте.
function renderImage(width, height, minLat, minLon, maxLat, maxLon) {
    var url = `/render?width=${width}&height=${height}&minLat=${minLat}&minLon=${minLon}&maxLat=${maxLat}&maxLon=${maxLon}`;

    fetch(url)
        .then(response => response.blob())
        .then(blob => {
            var imageUrl = URL.createObjectURL(blob);

            // Создаем слой с изображением и добавляем его на карту.
            var imageLayer = L.imageOverlay(imageUrl, [[minLat, minLon], [maxLat, maxLon]]);
            imageLayer.addTo(map);
        })
        .catch(error => console.error('Ошибка при загрузке изображения:', error));
}

// Вызываем функцию renderImage с необходимыми параметрами.
renderImage(800, 600, 51.5, -0.1, 51.51, -0.09);
