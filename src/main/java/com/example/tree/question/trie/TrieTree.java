package com.example.tree.question.trie;

/**
 * 前缀树，又叫"字典树".
 *给定一个字符串数组和一个指定的字符串，查找该数组中是否有该字符串，以及以该字符串为前缀的字符串，有则返回相应的数量。
 */
public class TrieTree {
    public TrieTreeNode root;

    public TrieTree() {
        root = new TrieTreeNode();
    }

    /**
     * 插入其实是创建字符串数组的动作。
     * @param str
     */
    public void insert(String str) {
        if(str==null){
            return;
        }
        char[] chars = str.toCharArray();
        int index = 0;
        TrieTreeNode node = root;
        node.pass++;
        for (int i = 0; i < chars.length; i++) {
            index = chars[i] - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieTreeNode();
            }
            node.nexts[index].pass++;
            node = node.nexts[index];
        }
        node.end++;
    }

    /**
     * 查找指定字符串在原字符串数组中的出现次数。
     * @param str
     * @return
     */
    public int search(String str) {
        if (root == null || str == null) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int index = 0;
        TrieTreeNode node = root;
        for (int i = 0; i < chars.length; i++) {
            index = chars[i] - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.end;
    }

    /**
     * 查询原字符串数组中是否有以 str 为前缀的字符串，返回数量。
     * @param str
     * @return
     */
    public int searchPrefix(String str) {
        if (root == null || str == null) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int index = 0;
        TrieTreeNode node = root;
        for (int i = 0; i < chars.length; i++) {
            index = chars[i] - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.pass;
    }

    /**
     * 删除字符串数组中的 str.
     * @param str
     */
    public void delete(String str) {
        if (root == null || str == null) {
            return;
        }
        //如果原字符串数组中不存在 str， return.
        int num = search(str);
        if (num == 0) {
            return;
        }

        char[] chars = str.toCharArray();
        int index = 0;
        TrieTreeNode node = root;
        node.pass--;
        for (char aChar : chars) {
            index = aChar - 'a';
            node.nexts[index].pass--;
            if (node.nexts[index].pass == 0) {
                node.nexts[index] = null;
                return;
            }
            node = node.nexts[index];
        }
        node.end--;
    }
}





















