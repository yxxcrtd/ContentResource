jQuery.namespace = function() {  
    var a=arguments, o=null, i, j, d;  
    for (i=0; i<a.length; i=i+1) {  
        d=a[i].split(".");  
        o=window;  
        for (j=0; j<d.length; j=j+1) {  
            o[d[j]]=o[d[j]] || {};  
            o=o[d[j]];  
        }  
    }  
    return o;  
};
/**********图书***************/
jQuery.namespace('Content.Product.ProductInfo');
/**********分类***************/
jQuery.namespace('Content.ProductType.Info');
/**********图片***************/
jQuery.namespace('Content.Picture.PictureInfo');
/**********最新活动***************/
jQuery.namespace('Content.Latest.ActivityInfo');
/**********作者版权管理***************/
jQuery.namespace('Content.Copyright.AuthorInfo');
/**********授权管理***************/
jQuery.namespace('Content.Authorization.Info');
/**********产品包管理***************/
jQuery.namespace('Content.Compress.Info');
