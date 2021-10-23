package com.example.mygymclub.models;

public class EvaluationMapper {
    private IEvaluation Evaluation;

    public EvaluationMapper(IEvaluation evaluation) {
        Evaluation = evaluation;
    }

    public Evaluation toBase(){
        Evaluation baseEvaluation = new Evaluation(
                this.Evaluation.getDate(),
                this.Evaluation.getWeight(),
                this.Evaluation.getUserId()

        );
        baseEvaluation.setId(this.Evaluation.getId());
        return baseEvaluation;
    }

    public EvaluationEntity toEntity(){
        return new EvaluationEntity(
                this.Evaluation.getId(),
                this.Evaluation.getDate(),
                this.Evaluation.getWeight(),
                this.Evaluation.getUserId()
        );

    }
}
