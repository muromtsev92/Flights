1. Исходный json конвертируем с помощью метода convertJsonToObject(String filePath) в объект DailyData, который содержит информацию о полетах и погоде за одни сутки. Если файла нет в указанной директории, то выбрасываем исключение.
2. Создаем файл менеджер расписания, который принимает файл DailyData
3. Внутри менеджера реализована логика проверки погодных условий каждого полета с учетом разницы во времени между городами. Поправки по времени вносятся в мапу заранее через статистический блок инициализации.
4. Проверив все рейсы, в консоль выводится информация о том, состоится ли рейс
