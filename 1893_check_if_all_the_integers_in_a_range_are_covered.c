bool isCovered(int** ranges, int rangesSize, int* rangesColSize, int left, int right) {
    int iplace[52]={0};
    for(int i=0;i<rangesSize;i++){
        int start=ranges[i][0];
        int end=ranges[i][1];
        iplace[start]++;
        iplace[end+1]--;
    }
        for(int i=1;i<=right;i++){
            iplace[i]=iplace[i]+iplace[i-1];
        }
    for(int i=left;i<=right;i++){
        if(iplace[i]<=0){
            return 0;
        }
    }
return 1;
    
}
