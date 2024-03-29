# Игра "Морской бой"

Это консольная версия классической игры "Морской бой" на Java. Программа предоставляет два режима игры: игру с другом и игру с компьютером.

## Запуск игры

1. Убедитесь, что на вашем компьютере установлена Java Runtime Environment (JRE) или используйте готовый файл в каталоге.

2. Скомпилируйте исходный код и создайте исполняемый JAR-файл. Например, используйте команду:

   ```bash
   javac -d out src/main/java/com/seabattle/*.java
   jar cfe SeaBattleGame.jar com.seabattle.BattleshipGame -C out/ .
   
Перейдите в каталог с вашим .jar файлом.

Откройте командную строку (Command Prompt) или терминал в этом каталоге.

Введите следующую команду для запуска игры:
java -jar SeaBattleGame.jar

Игровые режимы
1. Игра с другом
Выберите режим игры "Игра с другом" при запуске приложения. Затем введите имена двух игроков. Следуйте инструкциям для размещения кораблей и совершения ходов.

2. Игра с компьютером
Выберите режим игры "Игра с компьютером" при запуске приложения. Введите ваше имя. Следуйте инструкциям для размещения кораблей. Компьютер будет автоматически совершать свои ходы.

Размещение кораблей
Выберите режим размещения кораблей:

1: Ручной режим.
2: Автоматический режим.
Если выбран ручной режим:

Введите координаты X и Y для каждого корабля.
Выберите направление корабля (вертикальное или горизонтальное).
Если выбран автоматический режим:

Корабли будут размещены автоматически согласно стандартным правилам.
Совершение хода
Введите координаты X и Y для вашего хода.

Игровое поле будет отображаться с результатом хода:

-: Пустая клетка.
*: Попадание в корабль.
X: Мимо.
Повторите шаги для совершения ходов взаимодействующих игроков.
Победа
Игра продолжается, пока один из игроков не потопит все корабли противника. Как только это произойдет, игра завершается, и объявляется победитель.

Завершение игры
Игру можно завершить в любой момент, введя соответствующую команду.
По всем вопросам или рекомендациям по улучшению обращаться сюда: andrewmail2001@gmail.com
