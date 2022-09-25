package UnionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind {
    // 对样本进行包裹（元素）
    private static class Element<V> {
        public V value;

        public Element(V value) {
            this.value = value;
        }
    }

    public static class UnionFindSet<V> {
        // 样本与元素的对应
        public HashMap<V, Element<V>> elementMap;
        // key 某个元素 value 元素的父
        public HashMap<Element<V>, Element<V>> fatherMap;
        // key 某个集合的代表元素 value 集合的大小
        public HashMap<Element<V>, Integer> sizeMap;

        /**
         * 初始化并查集
         */
        public UnionFindSet(List<V> list) {
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            // 初始化
            for (V value : list) {
                // 进行包裹
                Element<V> element = new Element<V>(value);
                // 样本与元素一一对应
                elementMap.put(value, element);
                // 父节点（代表元素）都是自己
                fatherMap.put(element, element);
                // 集合大小都为1（只有本身）
                sizeMap.put(element, 1);
            }
        }

        /**
         * 查找元素的代表元素
         */
        private Element<V> findHead(Element<V> element) {
            // 代表元素不是本身时，放入栈中，且一直往上找
            Stack<Element<V>> path = new Stack<>();
            while (element != fatherMap.get(element)) {
                path.push(element);
                element = fatherMap.get(element);
            }
            // 找到代表元素后，将栈中所有子节点的代表元素置为最顶层代表元素
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), element);
            }
            return element;
        }

        /**
         * 判断两样本是否在同一集合
         */
        public boolean isSameSet(V a, V b) {
            // 并查集中是否有该元素（是否初始化）
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                // 代表元素是否相同
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        /**
         * 合并集合
         */
        public void union(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                // 获取对应元素
                Element<V> aFather = findHead(elementMap.get(a));
                Element<V> bFather = findHead(elementMap.get(b));
                // 不在同一集合时，将节点少的集合添加到节点多的集合中
                if (aFather != bFather) {
                    Element<V> big = sizeMap.get(aFather) >= sizeMap.get(bFather) ? aFather : bFather;
                    Element<V> small = big == aFather ? bFather : aFather;
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(aFather) + sizeMap.get(bFather));
                    sizeMap.remove(small);
                }
            }
        }
    }

}