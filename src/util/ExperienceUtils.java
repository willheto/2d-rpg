package util;

import main.GamePanel;

public class ExperienceUtils {
    GamePanel gamePanel;

    public ExperienceUtils(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public static long calculateExperienceDifference(int level) {
        if (level < 2) {
            throw new IllegalArgumentException("Level must be 2 or higher.");
        }
        double exponentPart = Math.pow(2, (double) (level - 1) / 7);
        double expressionValue = (level - 1) + 300 * exponentPart;
        double result = (1.0 / 4) * expressionValue;
        long flooredValue = (long) Math.floor(result);

        return flooredValue;
    }

    public static int getLevelFromExperience(long experience) {
        int level = 1;
        long accumulatedExperience = 0;

        while (true) {
            long experienceDifference = calculateExperienceDifference(level + 1);
            if (accumulatedExperience + experienceDifference > experience) {
                break;
            }
            accumulatedExperience += experienceDifference;
            level++;
        }

        return level;
    }

    public static long getExperienceUntilNextLevel(long experience) {
        int currentLevel = getLevelFromExperience(experience);
        long experienceDifference = calculateExperienceDifference(currentLevel + 1);
        long experienceUntilNextLevel = experienceDifference
                - (experience - calculateExperienceDifference(currentLevel));

        return experienceUntilNextLevel;
    }
}
