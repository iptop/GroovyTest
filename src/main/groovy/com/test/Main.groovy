package com.test

class Main {
    static long IP2Long(String ip){
        long ret =0;
        String[] sa = ip.split("\\.")
        if(sa.length==4){
            ret = Long.parseLong(sa[0])
            ret *= 256
            ret += Long.parseLong(sa[1])
            ret *= 256
            ret += Long.parseLong(sa[2])
            ret *= 256
            ret += Long.parseLong(sa[3])
        }
        return  ret
    }

    static String Long2IP(long l){
        String ret =""
        Long a,b,c,d
        d= l%256;l=l/256
        c= l%256;l=l/256
        b= l%256;l=l/256
        a= l
        ret = a+"."+b+"."+c+"."+d
        return ret
    }

    static void main(String[] args){
        if(args.length<2){
            println("输入参数错误")
            return
        }
        def inpath = args[0]
        def outpath = args[1]
        println("输入文件"+inpath)
        println("输出文件"+outpath)
        def file = new File(outpath)
        if (file.exists())
            file.delete()
        def printWriter = file.newPrintWriter()
        /*读取文件*/
        try{
             new File(inpath).eachLine {
                 def str = it
                 def l= str.length()

                 while({
                     str=str.replace("  "," ")
                     str.length()!=l
                 }()){
                     l=str.length()
                 }
                 def (start,end) = str.split(" ")
                 if(start!="" && end != ""){
                     for(def i=IP2Long(start);i<=IP2Long(end);i++){
                         if(i%256!=0){
                             printWriter.write(Long2IP(i))
                             printWriter.write('\r\n')
                             println( Long2IP(i) )
                         }
                     }
                 }
             }
        }catch (Exception ex){
            println("读文件异常")
            ex.printStackTrace()
        }



        printWriter.flush()
        printWriter.close()


    }
}