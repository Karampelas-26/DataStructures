public class Sort {


    public Sort() {
    }

    public void quickSort(City[] arr, int start, int end){
        if (end <= start) return;
        int i = partition(arr, start, end);
        quickSort(arr, start, i-1);
        quickSort(arr, i+1, end);
    }

    private int partition(City[] array, int left, int right) {
        City pivot = array[right];

        int partitionIndex = left;
        for (int i =left; i< right; i++){
            if (array[i].compareTo(pivot) > 0){ //descending order
                swap(array, i, partitionIndex);
                partitionIndex++;
            } else if (array[i].compareTo(pivot) == 0) {
                String str1 = array[i].getName();
                String str2 = pivot.getName();
                if (str1.compareTo(str2) < 0) {
                    swap(array, i, partitionIndex);
                    partitionIndex++;
                } else if (str1.equals(str2)) {
                    if (array[i].getID() < pivot.getID()) {
                        swap(array, i, partitionIndex);
                        partitionIndex++;
                    }
                }
            }
        }

        swap(array, partitionIndex, right);

        return partitionIndex;
    }

    private void swap(City arr[], int i, int j) {
        City temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
