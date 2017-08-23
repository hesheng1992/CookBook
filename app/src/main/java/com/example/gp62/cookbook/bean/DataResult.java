package com.example.gp62.cookbook.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GP62 on 2017/8/17.
 */

public class DataResult {



    private String code;
    private boolean charge;
    private String msg;
    private ResultBeanX result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBeanX getResult() {
        return result;
    }

    public void setResult(ResultBeanX result) {
        this.result = result;
    }

    public static class ResultBeanX {


        private String msg;
        private ResultBean result;
        private String status;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static class ResultBean {

            private String num;
            private List<ListBean> list;

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean extends BaseData implements Parcelable{


                private String classid;
                private String preparetime;
                private String name;
                private String id;
                private String pic;
                private String tag;
                private String peoplenum;
                private String content;
                private String cookingtime;
                private List<ProcessBean> process;
                private List<MaterialBean> material;
                public ListBean(){

                }
                protected ListBean(Parcel in) {
                    classid = in.readString();
                    preparetime = in.readString();
                    name = in.readString();
                    id = in.readString();
                    pic = in.readString();
                    tag = in.readString();
                    peoplenum = in.readString();
                    content = in.readString();
                    cookingtime = in.readString();
                    //如果需要传递对象集合需要这么写
                    process=in.readArrayList(ProcessBean.class.getClassLoader());
                    material=in.readArrayList(MaterialBean.class.getClassLoader());
                }

                public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                    @Override
                    public ListBean createFromParcel(Parcel in) {
                        return new ListBean(in);
                    }

                    @Override
                    public ListBean[] newArray(int size) {
                        return new ListBean[size];
                    }
                };

                public String getClassid() {
                    return classid;
                }

                public void setClassid(String classid) {
                    this.classid = classid;
                }

                public String getPreparetime() {
                    return preparetime;
                }

                public void setPreparetime(String preparetime) {
                    this.preparetime = preparetime;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }

                public String getPeoplenum() {
                    return peoplenum;
                }

                public void setPeoplenum(String peoplenum) {
                    this.peoplenum = peoplenum;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getCookingtime() {
                    return cookingtime;
                }

                public void setCookingtime(String cookingtime) {
                    this.cookingtime = cookingtime;
                }

                public List<ProcessBean> getProcess() {
                    return process;
                }

                public void setProcess(List<ProcessBean> process) {
                    this.process = process;
                }

                public List<MaterialBean> getMaterial() {
                    return material;
                }

                public void setMaterial(List<MaterialBean> material) {
                    this.material = material;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(classid);
                    dest.writeString(preparetime);
                    dest.writeString(name);
                    dest.writeString(id);
                    dest.writeString(pic);
                    dest.writeString(tag);
                    dest.writeString(peoplenum);
                    dest.writeString(content);
                    dest.writeString(cookingtime);
                    //如果需要传递对象集合需要这么写
                    dest.writeList(process);
                    dest.writeList(material);
                }

                public static class ProcessBean implements Parcelable{
                    /**
                     * pcontent : 准备食材。
                     * pic : http://api.jisuapi.com/recipe/upload/20160719/162550_84583.jpg
                     */

                    private String pcontent;
                    private String pic;

                    protected ProcessBean(Parcel in) {
                        pcontent = in.readString();
                        pic = in.readString();
                    }

                    public static final Creator<ProcessBean> CREATOR = new Creator<ProcessBean>() {
                        @Override
                        public ProcessBean createFromParcel(Parcel in) {
                            return new ProcessBean(in);
                        }

                        @Override
                        public ProcessBean[] newArray(int size) {
                            return new ProcessBean[size];
                        }
                    };

                    public String getPcontent() {
                        return pcontent;
                    }

                    public void setPcontent(String pcontent) {
                        this.pcontent = pcontent;
                    }

                    public String getPic() {
                        return pic;
                    }

                    public void setPic(String pic) {
                        this.pic = pic;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(pcontent);
                        dest.writeString(pic);
                    }
                }

                public static class MaterialBean implements Parcelable{
                    /**
                     * amount : 适量
                     * mname : 油
                     * type : 0
                     */

                    private String amount;
                    private String mname;
                    private String type;

                    protected MaterialBean(Parcel in) {
                        amount = in.readString();
                        mname = in.readString();
                        type = in.readString();
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(amount);
                        dest.writeString(mname);
                        dest.writeString(type);
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    public static final Creator<MaterialBean> CREATOR = new Creator<MaterialBean>() {
                        @Override
                        public MaterialBean createFromParcel(Parcel in) {
                            return new MaterialBean(in);
                        }

                        @Override
                        public MaterialBean[] newArray(int size) {
                            return new MaterialBean[size];
                        }
                    };

                    public String getAmount() {
                        return amount;
                    }

                    public void setAmount(String amount) {
                        this.amount = amount;
                    }

                    public String getMname() {
                        return mname;
                    }

                    public void setMname(String mname) {
                        this.mname = mname;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }
                }
            }
        }
    }
}
