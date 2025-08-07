import java.util.*;

public class RecursiveTreePreview {
    static class Folder {
        String name;
        List<Object> contents;
        Folder(String name) {
            this.name = name;
            this.contents = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        Folder root = new Folder("root");
        root.contents.add("file1.txt");
        Folder sub1 = new Folder("sub1");
        sub1.contents.add("file2.txt");
        Folder sub2 = new Folder("sub2");
        sub2.contents.add("file3.txt");
        sub2.contents.add("file4.txt");
        sub1.contents.add(sub2);
        root.contents.add(sub1);
        root.contents.add("file5.txt");

        System.out.println("總檔案數：" + countFiles(root));

        Map<String, Object> menu = new LinkedHashMap<>();
        menu.put("File", Map.of("New", null, "Open", null));
        menu.put("Edit", Map.of("Undo", null, "Redo", null));
        menu.put("View", null);
        printMenu(menu, 0);

        Object[] nestedArray = {1, new Object[]{2, 3, new Object[]{4}}, 5};
        List<Object> flat = new ArrayList<>();
        flatten(nestedArray, flat);
        System.out.println("展平陣列：" + flat);

        List<Object> nestedList = List.of(1, List.of(2, List.of(3, 4), 5), 6);
        System.out.println("最大深度：" + maxDepth(nestedList));
    }

    static int countFiles(Folder folder) {
        int count = 0;
        for (Object o : folder.contents) {
            if (o instanceof String) count++;
            else if (o instanceof Folder) count += countFiles((Folder) o);
        }
        return count;
    }

    static void printMenu(Map<String, Object> menu, int level) {
        for (Map.Entry<String, Object> e : menu.entrySet()) {
            for (int i = 0; i < level; i++) System.out.print("  ");
            System.out.println("- " + e.getKey());
            if (e.getValue() instanceof Map)
                printMenu((Map<String, Object>) e.getValue(), level + 1);
        }
    }

    static void flatten(Object[] arr, List<Object> result) {
        for (Object o : arr) {
            if (o instanceof Object[])
                flatten((Object[]) o, result);
            else
                result.add(o);
        }
    }

    static int maxDepth(List<?> list) {
        int max = 1;
        for (Object o : list) {
            if (o instanceof List) {
                int depth = 1 + maxDepth((List<?>) o);
                if (depth > max) max = depth;
            }
        }
        return max;
    }
}
