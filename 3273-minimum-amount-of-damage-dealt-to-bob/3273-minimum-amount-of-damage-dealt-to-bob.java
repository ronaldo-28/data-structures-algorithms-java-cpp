class Solution {
    private class Enemy {
        int timeToKill;
        int damage;

        public Enemy(int timeToKill, int damage) {
            this.timeToKill = timeToKill;
            this.damage = damage;
        }
    }

    public long minDamage(int power, int[] damage, int[] health) {
        Enemy[] enemies = new Enemy[damage.length];
        for (int i = 0; i < damage.length; i++) {
            int ttk = Math.ceilDiv(health[i], power);
            enemies[i] = new Enemy(ttk, damage[i]);
        }
        // Sort by highest ratio of damage:timeToKill
        Arrays.sort(enemies, (a,b) -> Long.compare(b.damage * a.timeToKill, a.damage * b.timeToKill));

        long time = 0;
        long minDamage = 0;

        for (int i = 0; i < enemies.length; i++) {
            time += enemies[i].timeToKill;
            minDamage += time * enemies[i].damage;
        }

        return minDamage;
    }
}