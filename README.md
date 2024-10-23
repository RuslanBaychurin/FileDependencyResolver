# File Dependency Resolver

## Описание

File Dependency Resolver — это программа на Java, которая ищет текстовые файлы в заданной корневой папке и ее подкаталогах, извлекает зависимости между ними, сортирует их по именам и объединяет содержимое в один файл. Программа обрабатывает директивы `*require*`, указывающие на зависимости между файлами, и выводит сообщение об ошибке в случае циклических зависимостей.

## Функциональность

- Рекурсивный обход папок для поиска текстовых файлов.
- Обработка зависимостей между файлами с помощью директив `*require*`.
- Топологическая сортировка файлов для правильного порядка объединения.
- Конкатенация содержимого файлов в один текстовый файл.

## Установка и запуск

### Требования

- Java Development Kit (JDK) 8 или выше.
- IntelliJ IDEA или другой текстовый редактор для редактирования кода.

### Инструкция по запуску

1. **Клонируйте репозиторий**:
   - Откройте терминал или командную строку на вашем компьютере.
   - Перейдите в папку, где вы хотите разместить проект:
     ```bash
     cd /путь/к/вашей/папке
     ```
   - Клонируйте репозиторий с помощью команды (замените `<URL_репозитория>` на фактический URL вашего репозитория):
     ```bash
     git clone <URL_репозитория>
     ```

2. **Откройте проект в IntelliJ IDEA**:
   - Запустите IntelliJ IDEA.
   - Выберите **File** → **Open** и укажите путь к клонированному репозиторию.

3. **Настройте корневую папку**:
   - Откройте файл `FileDependencyResolver.java` в папке `src`.
   - Найдите строку:
     ```java
     Path rootPath = Paths.get("root_directory"); // Замените на путь к корневой папке
     ```
   - Замените `"root_directory"` на фактический путь к корневой папке, где находятся ваши текстовые файлы и папки. Например:
     ```java
     Path rootPath = Paths.get("C:/Users/ВашеИмя/Path/To/Your/Files");
     ```

4. **Создайте папку для вывода**:
   - Убедитесь, что в корневом каталоге вашего проекта есть папка `output`, в которую программа будет записывать объединённое содержимое файлов.
   - Если папка отсутствует, создайте её вручную.

5. **Запустите программу**:
   - Щелкните правой кнопкой мыши на файле `FileDependencyResolver.java` в папке `src`.
   - Выберите **Run 'FileDependencyResolver.main()'**.
   - Программа начнет выполнение и создаст файл `output.txt` в папке `output` с объединённым содержимым всех текстовых файлов.
