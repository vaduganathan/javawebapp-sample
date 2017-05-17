package backup.dxp;

import com.coremedia.blueprint.common.contentbeans.CMObject;
import com.coremedia.cap.feeder.bean.PropertyConverter;
import com.coremedia.objectserver.dataviews.DataViewFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RelatedPropertyConverter implements PropertyConverter {
  private static final Logger LOGGER = LoggerFactory.getLogger(RelatedPropertyConverter.class);
  private static final String METHOD_PARENT = "getParent";
  private static final String METHOD_ID = "getId";

  private boolean ignoreParents;
  private TreePathKeyFactory pathKeyFactory;
  private DataViewFactory dataViewFactory;

  @Override
  public Object convertValue(Object object) {
    setDataViewFactory(dataViewFactory);


    if (object instanceof List) {

//      @todo:
//      - prüfen ob object eine list und die methode getParent enthält und flag ignoreParents gesetzt -> erledigt
//WEITER:
//      - ja: alle parents lesen und id speichern
//        - nein, keine list: keine for schleife -> nächster Punkt -> erledigt
//        - nein, keine getParent: element wird als root behandelt
//        - nein, kein flag: s. punkt 2
//      ? prüfen, ob object = CMObject -> was wenn nicht?

      Set<String> tags = new TreeSet<>();
      List<CMObject> cmObjects = (List<CMObject>) object;
      List cachedObjects = null;
      for(CMObject cmObject : cmObjects) {
        if(cmObject instanceof CMObject) {
          if(!ignoreParents && hasObjectMethod(METHOD_PARENT, cmObjects)) {
  //          read parents
          }
//        } else if(cmObject instanceof CMObject) {
//          only store current element id
//          @todo: object aus speicher ???
          cachedObjects.add(dataViewFactory.loadCached(cmObject, null).toString());
        }
      }
      tags.addAll(cachedObjects);

//      @SuppressWarnings("unchecked")
//      List<CMObject> cmObjects = (List<CMObject>) object;
//      Set<String> tags = new TreeSet<>();




      if(ignoreParents) {
//        for(CMTaxonomy taxonomy: taxonomies) {
//          // obtain the path segment (id or value)
//          String tag = taxonomyPathKeyFactory.getPathSegment(taxonomy.getContent());
//          tags.add(tag);
//        }
      } else {
//        for(CMObject cmObject: cmObjects) {
          // do the (recursive) fragment cache key lookup
//          String path = taxonomyPathKeyFactory.getPath(taxonomy.getContent());
//          // path starts with a slash and is separated by slashes. Ignore the empty token in the beginning.
//          String[] strings = StringUtils.tokenizeToStringArray(path, "/", false, true);
//          tags.addAll(Arrays.asList(strings));
//        }
      }
//      if(tags.isEmpty()){
//        return null;
//      }
//      // convert the Set to a string like "2,10,6,4,8". order is irrelevant for solr.
//      String convertedValue = StringUtils.collectionToDelimitedString(tags, ",", "", "");
//      LOGGER.debug("Converted into: {}", convertedValue);
//      return convertedValue;
      return getConvertedValue(tags);
    } else if(object instanceof CMObject) {
//      @todo:
//      - objekt id lesen und speichern
      Set<String> tag = (hasObjectMethod(METHOD_ID, object)) ? object.getId() : null;
      return getConvertedValue(tag);
    } else {
      return null;
    }
  }

  private String getConvertedValue(Set<String> tags) {
    // convert the Set to a string like "2,10,6,4,8". order is irrelevant for solr.
    String convertedValue = StringUtils.collectionToDelimitedString(tags, ",", "", "");
    LOGGER.debug("Converted into: {}", convertedValue);
    return convertedValue;
  }

  private boolean hasObjectMethod(String methodName, Object object) {
    try {
      object.getClass().getMethod(methodName, (Class<?>)null);
      return true;
    } catch(NoSuchMethodException e) {
      return false;
    }
  }

  private boolean hasObjectParent(Object object) {

  }

  private void setDataViewFactory(DataViewFactory dataViewFactory) {
    this.dataViewFactory = dataViewFactory;
  }

  @Override
  public Class<?> convertType(Class<?> type) {
    return String.class;
  }

  /**
   * In some use cases the parents of a taxonomy are not required. This can be achieved by setting the parameter to true
   * @param ignoreParents whether parents can be ignored, default is false
   */
  public void setIgnoreParents(boolean ignoreParents) {
    this.ignoreParents = ignoreParents;
  }

//  public void setTaxonomyPathKeyFactory(TreePathKeyFactory taxonomyPathKeyFactory) {
//    this.taxonomyPathKeyFactory = taxonomyPathKeyFactory;
//  }
}
