package br.csi.lucene;

import br.csi.model.Tarefa;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class luceneDAO {
    private static final String INDEX_DIR = "/home/camargo/IdeaProjects/APILuceneeSpring/src/main/webapp/WEB-INF/txtsIndexado";//diretorio do arquivo invertido

    /**
     *
     * @param tarefa texto que vai ser cadastrado
     * @return
     * @throws IOException
     */
    public boolean cadastraTexto(Tarefa tarefa) throws IOException {
        try{
            List<String> lines = Arrays.asList(tarefa.getDescricao());
            Path dir = Paths.get("/home/camargo/IdeaProjects/APILuceneeSpring/src/main/webapp/WEB-INF/txts");
            Path file = dir.resolve(tarefa.getTitulo()+".txt");
            Files.write(file, lines, Charset.forName("UTF-8"));

            pegadados(tarefa);

            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * pega os dados do txt e indexa o arquivo
     * @param tarefa pega os dados do txt e indexa o arquivo
     * @return
     * @throws Exception
     */
    public boolean pegadados(Tarefa tarefa) throws Exception {
        System.out.println("tarefa descrição: "+tarefa.getDescricao());
        System.out.println("tarefa titulos: "+tarefa.getTitulo());

        IndexWriter writer = createWriter();//c

        writer.deleteAll();//

        List<Document> documents = new ArrayList<>();

        String dataDir = "/home/camargo/IdeaProjects/APILuceneeSpring/src/main/webapp/WEB-INF/txts";//diretorio onde fica os txts criados

        File[] files = new File(dataDir).listFiles();

        try {
            for (File f: files)
            {
                if (!f.isDirectory() &&
                        !f.isHidden() &&
                        f.exists() &&
                        f.canRead()) {

                    System.out.println("Indexando o txt: " + f.getCanonicalPath());
                    documents.add(indexFile(f));
                }
            }

            writer.addDocuments(documents);
            writer.commit();
            writer.close();

            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * faz a pesquisa do string inserida pelo usuario
     * @param pesquisa
     * @return
     * @throws Exception
     */
    public ArrayList<Document> Pesquisa(String pesquisa) throws Exception {
        IndexSearcher searcher = createSearcher();

        TopDocs foundDocs2 = searchByContents(pesquisa, searcher);
       /* System.out.println("Total Results :: " + foundDocs2.totalHits);*/

        ArrayList<Document> documentos= new ArrayList<Document>();
        for (ScoreDoc sd : foundDocs2.scoreDocs)
        {
            Document d = searcher.doc(sd.doc);
            System.out.println(String.format(d.get("filename")));
            System.out.println(String.format(d.get("fullpath")));
            System.out.println(sd.score);
            documentos.add(d);

        }
        /*System.out.println("\n\nDocumentos: "+documentos+"\n\n");*/
        return documentos;

    }

    /**
     * cria os indexs invertidos dos arquis
     * @return retorna os index
     * @throws IOException
     */
    private static IndexWriter createWriter() throws IOException
    {
        FSDirectory dir = FSDirectory.open(Paths.get(INDEX_DIR));
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        IndexWriter writer = new IndexWriter(dir, config);
        return writer;
    }

    /**
     * le os docs
     * @param f
     * @return
     * @throws Exception
     */
    protected static Document getDocument(File f) throws Exception {
        Document doc = new Document();
        doc.add(new TextField("contents", new FileReader(f)));
        doc.add(new StringField("filename", f.getName(), Field.Store.YES));
        doc.add(new StringField("fullpath", f.getCanonicalPath(),Field.Store.YES));
        return doc;
    }

    private static Document indexFile(File f) throws Exception {
        Document doc = getDocument(f);
        return doc;
    }

    private static TopDocs searchByContents(String x, IndexSearcher searcher) throws Exception
    {
        QueryParser qp = new QueryParser("contents", new StandardAnalyzer());
        Query contentsQuery = qp.parse(x);
        TopDocs hits = searcher.search(contentsQuery, 10);
        return hits;
    }

    /**
     *
     * @return
     * @throws IOException
     */
    private static IndexSearcher createSearcher() throws IOException {
        Directory dir = FSDirectory.open(Paths.get(INDEX_DIR));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        return searcher;
    }
}
