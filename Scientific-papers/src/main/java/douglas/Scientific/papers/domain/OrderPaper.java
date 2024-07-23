package douglas.Scientific.papers.domain;

import douglas.Scientific.papers.domain.enums.FileType;
import douglas.Scientific.papers.domain.enums.KnowledgeArea;



public class OrderPaper {
    private String paperTitle;
    private String paperAbstract;
    private FileType fileType;
    private KnowledgeArea knowledgeArea;


    public OrderPaper(String paperTitle, FileType fileType, KnowledgeArea knowledgeArea) {
        this.paperTitle = paperTitle;
        this.paperAbstract = paperAbstract;
        this.fileType = fileType;
        this.knowledgeArea = knowledgeArea;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperAbstract() {
        return paperAbstract;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public KnowledgeArea getKnowledgeArea() {
        return knowledgeArea;
    }

    public void setKnowledgeArea(KnowledgeArea knowledgeArea) {
        this.knowledgeArea = knowledgeArea;
    }
}
